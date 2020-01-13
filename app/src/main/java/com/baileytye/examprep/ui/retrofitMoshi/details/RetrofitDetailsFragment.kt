package com.baileytye.examprep.ui.retrofitMoshi.details

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.baileytye.examprep.R
import com.baileytye.examprep.databinding.FragmentRetrofitDetailsBinding
import com.baileytye.examprep.util.load

class RetrofitDetailsFragment : Fragment() {

    private lateinit var binding: FragmentRetrofitDetailsBinding
    private val navArgs: RetrofitDetailsFragmentArgs by navArgs()
    private val viewModel: RetrofitDetailsViewModel by viewModels {
        RetrofitDetailsViewModelFactory(
            navArgs.marsProperty
        )
    }
    private val property by lazy { navArgs.marsProperty }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_retrofit_details, container, false)

        binding.let {
            it.lifecycleOwner = this
            it.viewModel = viewModel
            it.imageViewHeader.load(property.imgSrcUrl) {
                //Not sure if I can bind this in XMl since I need fragment scope to postpone transition
                startPostponedEnterTransition()
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ViewCompat.setTransitionName(binding.imageViewHeader, "image_${property.id}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postponeEnterTransition()
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }
}
