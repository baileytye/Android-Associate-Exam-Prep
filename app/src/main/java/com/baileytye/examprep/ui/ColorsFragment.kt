package com.baileytye.examprep.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.baileytye.examprep.R
import com.baileytye.examprep.databinding.FragmentColorsBinding

/**
 * A simple [Fragment] subclass.
 */
class ColorsFragment : Fragment() {

    lateinit var binding: FragmentColorsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_colors, container, false
        )
        return binding.root
    }


}
