package com.baileytye.examprep.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.baileytye.examprep.R
import com.baileytye.examprep.data.User
import com.baileytye.examprep.databinding.FragmentHomeBinding
import com.baileytye.examprep.util.hideKeyboard

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home, container, false
        )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.apply {
            buttonMirrorFragment.setOnClickListener {
                this@HomeFragment.hideKeyboard()
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToMirrorTextFragment())
            }

            buttonSendUser.setOnClickListener {
                this@HomeFragment.hideKeyboard()
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToReceiveTextFragment(
                        User(editTextFirstName.text.toString(), editTextLastName.text.toString())
                    )
                )
            }
            buttonRecyclerView.setOnClickListener {
                this@HomeFragment.hideKeyboard()
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToUserListFragment())
            }
        }
    }
}
