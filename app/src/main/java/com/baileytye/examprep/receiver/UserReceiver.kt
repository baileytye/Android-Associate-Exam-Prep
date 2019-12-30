package com.baileytye.examprep.receiver

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.baileytye.examprep.data.User
import com.baileytye.examprep.util.sendNotification

class UserReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {

        val notificationManager = ContextCompat.getSystemService(
            context,
            NotificationManager::class.java
        ) as NotificationManager

        notificationManager.sendNotification(
            "Broadcast Receiver",
            context,
            Bundle().apply { putParcelable("receivedUser", User("Broadcast", "Receiver")) }
        )
    }

}