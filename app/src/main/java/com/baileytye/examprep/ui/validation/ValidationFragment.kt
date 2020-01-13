package com.baileytye.examprep.ui.validation

import android.animation.LayoutTransition
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.baileytye.examprep.R
import com.baileytye.examprep.data.EventObserver
import com.baileytye.examprep.databinding.FragmentValidationBinding
import com.baileytye.examprep.util.hideKeyboard
import com.google.android.material.snackbar.Snackbar

class ValidationFragment : Fragment() {

    private val viewModel: ValidationViewModel by viewModels()
    private lateinit var binding: FragmentValidationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_validation, container, false)
        binding.let {
            it.viewModel = viewModel
            it.lifecycleOwner = this
        }

        viewModel.showSnackBarEvent.observe(viewLifecycleOwner, EventObserver {
            if (it) {
                activity?.let { activity ->
                    hideKeyboard()
                    Snackbar.make(
                        activity.findViewById(android.R.id.content),
                        getString(R.string.message_valid),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        })

        container?.layoutTransition?.enableTransitionType(LayoutTransition.CHANGING)
        return binding.root
    }

}
