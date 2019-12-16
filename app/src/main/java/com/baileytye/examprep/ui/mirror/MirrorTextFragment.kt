package com.baileytye.examprep.ui.mirror


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.baileytye.examprep.R
import com.baileytye.examprep.databinding.FragmentMirrorTextBinding

/**
 * A simple [Fragment] subclass.
 */
class MirrorTextFragment : Fragment() {

    lateinit var binding: FragmentMirrorTextBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_mirror_text, container, false
        )

        binding.apply {
            binding.mirrorViewModel =
                ViewModelProvider(this@MirrorTextFragment)[MirrorViewModel::class.java]
            binding.lifecycleOwner = this@MirrorTextFragment.viewLifecycleOwner
        }

        return binding.root
    }


}
