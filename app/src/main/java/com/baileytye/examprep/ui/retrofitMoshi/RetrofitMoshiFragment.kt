package com.baileytye.examprep.ui.retrofitMoshi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.baileytye.examprep.R
import com.baileytye.examprep.data.EventObserver
import com.baileytye.examprep.databinding.FragmentRetrofitMoshiBinding
import com.baileytye.examprep.util.addSpacing

/**
 * Known issue: When returning from details, the recyclerView does not scroll back to where you were
 */
class RetrofitMoshiFragment : Fragment() {

    private val viewModel: RetrofitMoshiViewModel by viewModels()
    private lateinit var binding: FragmentRetrofitMoshiBinding
    private var clickedView: ImageView? = null
    private val recyclerAdapter by lazy {
        RetrofitAdapter(ItemClickListener { item, view ->
            clickedView = view
            viewModel.displayPropertyDetails(item)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_retrofit_moshi,
            container,
            false
        )
        binding.let {
            it.viewModel = viewModel
            it.lifecycleOwner = this
            it.recyclerViewRetrofit.apply {
                adapter = recyclerAdapter
                postponeEnterTransition()
                viewTreeObserver.addOnPreDrawListener {
                    startPostponedEnterTransition()
                    true
                }
                addSpacing()

            }
        }
        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, EventObserver { prop ->
            prop.let {
                val extras = clickedView?.let { view ->
                    FragmentNavigatorExtras(view to "image_${it.id}")
                } ?: FragmentNavigatorExtras()

                findNavController().navigate(
                    RetrofitMoshiFragmentDirections.actionRetrofitMoshiFragmentToRetrofitDetailsFragment(
                        it
                    ), extras
                )
            }
        })
        return binding.root
    }
}
