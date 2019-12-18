package com.baileytye.examprep.ui.retrofitMoshi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.baileytye.examprep.R
import com.baileytye.examprep.data.Result

class RetrofitMoshiFragment : Fragment() {

    private lateinit var viewModel: RetrofitMoshiViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.retrofit_moshi_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[RetrofitMoshiViewModel::class.java]
        viewModel.properties.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Result.Loading -> {
                    println("DEBUG: Loading")
                }
                is Result.Success -> {
                    println("DEBUG: Success")
                }
                is Result.Error -> {
                    println("DEBUG: Error")
                }
            }
        })
    }

}
