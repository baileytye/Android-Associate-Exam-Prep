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
                UserListAdapter().apply { submitList(viewModel.userList.value) }
            userRecyclerView.addSpacing()
        }
        return binding.root
    }
}
