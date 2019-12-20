package com.baileytye.examprep.ui.user


import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.baileytye.examprep.R
import com.baileytye.examprep.data.EventObserver
import com.baileytye.examprep.databinding.FragmentReceiveUserBinding
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass.
 */
class ReceiveUserFragment : Fragment() {
    lateinit var binding: FragmentReceiveUserBinding
    private val args: ReceiveUserFragmentArgs by navArgs()
    private val viewModel: UserViewModel by viewModels { UserViewModelFactory(args.receivedUser) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_receive_user, container, false
        )
        setHasOptionsMenu(true)
        binding.apply {

            userViewModel = viewModel

            //Allows the live data to automatically update the views
            lifecycleOwner = this@ReceiveUserFragment.viewLifecycleOwner

            viewModel.showSnackBarEvent.observe(viewLifecycleOwner, EventObserver { show ->
                if (show) {
                    activity?.let { activity ->
                        Snackbar.make(
                            activity.findViewById(android.R.id.content),
                            getString(R.string.message_undo),
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }
            })
        }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_receive_user, menu)
        activity?.let {
            if (null == getShareIntent().resolveActivity(it.packageManager)) {
                menu.findItem(R.id.menuShare).isVisible = false
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuShare -> {
                startActivity(getShareIntent())
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getShareIntent(): Intent =
        ShareCompat.IntentBuilder.from(activity).setText(binding.receivedUser?.firstName)
            .setType("text/plain")
            .intent


}
