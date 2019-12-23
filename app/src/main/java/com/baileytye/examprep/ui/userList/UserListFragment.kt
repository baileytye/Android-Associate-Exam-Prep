package com.baileytye.examprep.ui.userList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.baileytye.examprep.R
import com.baileytye.examprep.databinding.FragmentUserListBinding

/**
 * Simple recycler databinding. For recyclers with items that each need to update based on live data
 * (toggle button inside item or something), use : https://spin.atomicobject.com/2019/06/08/kotlin-recyclerview-data-binding/
 */
class UserListFragment : Fragment() {

    private lateinit var viewModel: UserListViewModel
    private lateinit var binding: FragmentUserListBinding
    private val adapter by lazy {
        UserListAdapter(UserItemListener {
            viewModel.removeUser(it)
            println("DEBUG: item clicked, size = ${viewModel.userList.value?.size}")
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_user_list, container, false
        )
        viewModel = ViewModelProvider(this)[UserListViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.userRecyclerView.adapter = adapter
        return binding.root
    }
}
