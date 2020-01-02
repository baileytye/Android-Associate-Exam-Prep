package com.baileytye.examprep.ui.userList

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.baileytye.examprep.data.User
import com.baileytye.examprep.getOrAwaitValue
import org.hamcrest.Matchers.hasItem
import org.hamcrest.Matchers.not
import org.junit.Assert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config


@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class UserListViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun removeUser() {
        //GIVEN
        val userListViewModel = UserListViewModel()
        val userList = userListViewModel.userList
        val value: List<User> = userList.getOrAwaitValue()
        val item = value.first()

        //WHEN
        userListViewModel.removeUser(item)

        //THEN
        assertThat(value, not(hasItem(item)))
    }
}