package com.baileytye.examprep.ui.pager

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.baileytye.examprep.R
import com.baileytye.examprep.data.EventObserver
import com.baileytye.examprep.databinding.FragmentPagerBinding
import com.google.android.material.snackbar.Snackbar


class PagerFragment : Fragment() {

    private lateinit var viewModel: PagerViewModel
    private lateinit var binding: FragmentPagerBinding
    private val adapter = PagerAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pager, container, false)
        viewModel = ViewModelProvider(this).get(PagerViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.viewPager.apply {
            adapter = this@PagerFragment.adapter
            setPageTransformer(viewModel.transformer)
        }
        viewModel.showSnackBarEvent.observe(viewLifecycleOwner, EventObserver {
            if (it) {
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    getString(
                        R.string.message_transformer,
                        viewModel.transformer::class.simpleName
                    ),
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        })
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_pager, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuSwapTransformer -> {
                binding.viewPager.setPageTransformer(viewModel.toggleTransformer())
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
