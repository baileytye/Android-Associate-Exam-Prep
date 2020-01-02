package com.baileytye.examprep.ui


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.baileytye.examprep.R
import com.baileytye.examprep.databinding.FragmentAboutBinding
import com.baileytye.examprep.util.keyCounter
import com.baileytye.examprep.util.put

/**
 * A simple [Fragment] subclass.
 */
class AboutFragment : Fragment() {
    lateinit var binding: FragmentAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_about, container, false
        )
        binding.button.setOnClickListener {
            val sharedPref = activity?.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE
            )
            sharedPref?.put(keyCounter, 0)
        }
        return binding.root
    }


}
