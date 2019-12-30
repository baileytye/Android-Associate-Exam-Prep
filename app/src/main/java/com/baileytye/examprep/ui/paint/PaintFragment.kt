package com.baileytye.examprep.ui.paint

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider


class PaintFragment : Fragment() {
    private lateinit var viewModel: PaintViewModel

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PaintViewModel::class.java)
    }

}
