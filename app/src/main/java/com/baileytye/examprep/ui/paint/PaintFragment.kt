package com.baileytye.examprep.ui.paint

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment


class PaintFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val myCanvasView: MyCanvasView
        context!!.let {
            myCanvasView = MyCanvasView(it)
            myCanvasView.contentDescription = "Content description"
        }
        return myCanvasView
    }

}
