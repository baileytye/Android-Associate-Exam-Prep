package com.baileytye.examprep.ui.userList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.baileytye.examprep.R
import com.baileytye.examprep.databinding.FragmentUserListBinding
import com.baileytye.examprep.util.addSpacing


class UserListFragment : Fragment() {

    private lateinit var viewModel: UserListViewModel
    private lateinit var binding: FragmentUserListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_user_list, container, false
        )
        viewModel = ViewModelProvider(this)[UserListViewModel::class.java]
        binding.apply {
            userRecyclerView.adapter =
                UserListAdapter(UserItemListener {
                    viewModel.removeUser(it)
                    println("DEBUG: item clicked, size = ${viewModel.userList.value?.size}")
                }).apply { submitList(viewModel.userList.value) }
            userRecyclerView.addSpacing()
            viewModel.userList.observe(viewLifecycleOwner, Observer {
                (userRecyclerView.adapter as UserListAdapter).submitList(it.toList())
            })
        }


        return binding.root
    }
}
