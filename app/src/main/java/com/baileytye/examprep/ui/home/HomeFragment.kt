package com.baileytye.examprep.ui.home


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.baileytye.examprep.R
import com.baileytye.examprep.data.User
import com.baileytye.examprep.databinding.FragmentHomeBinding
import com.baileytye.examprep.util.get
import com.baileytye.examprep.util.hideKeyboard
import com.baileytye.examprep.util.keyCounter

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment(), HomeNavigator {

    lateinit var binding: FragmentHomeBinding
    val viewModel: HomeViewModel by viewModels { HomeViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home, container, false
        )
        viewModel.setNavigator(this)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val sharedPref = activity?.getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE
        )
        viewModel.counter.value = sharedPref?.get(keyCounter, 0)
    }

    override fun onStartMirrorText() {
        this.hideKeyboard()
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToMirrorTextFragment())
    }

    override fun onStartReceiveUser() {
        this.hideKeyboard()
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToReceiveTextFragment(
                User(
                    binding.editTextFirstName.text.toString(),
                    binding.editTextLastName.text.toString()
                )
            )
        )
    }

    override fun onStartUserList() {
        this.hideKeyboard()
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToUserListFragment())
    }

    override fun onStartRetrofit() {
        this.hideKeyboard()
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToRetrofitMoshiFragment())
    }
}
