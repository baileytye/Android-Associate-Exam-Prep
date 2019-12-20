package com.baileytye.examprep.ui.retrofitMoshi.details

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.baileytye.examprep.R
import com.baileytye.examprep.data.MarsProperty
import com.baileytye.examprep.databinding.FragmentRetrofitDetailsBinding

class RetrofitDetailsFragment : Fragment() {

    private lateinit var binding: FragmentRetrofitDetailsBinding
    private lateinit var viewModel: RetrofitDetailsViewModel
    private lateinit var property: MarsProperty

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_retrofit_details, container, false)

        property = RetrofitDetailsFragmentArgs.fromBundle(arguments!!).marsProperty
        viewModel = ViewModelProvider(
            this,
            RetrofitDetailsViewModelFactory(property)
        )[RetrofitDetailsViewModel::class.java]

        binding.let {
            it.lifecycleOwner = this
            it.viewModel = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ViewCompat.setTransitionName(binding.imageViewHeader, "image_${property.id}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }
}
