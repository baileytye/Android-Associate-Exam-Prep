package com.baileytye.examprep.ui.user


import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.baileytye.examprep.R
import com.baileytye.examprep.data.User
import com.baileytye.examprep.databinding.FragmentReceiveUserBinding
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass.
 */
class ReceiveUserFragment : Fragment() {
    lateinit var binding: FragmentReceiveUserBinding
    private val args: ReceiveUserFragmentArgs by navArgs()
    lateinit var viewModel: UserViewModel

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
            receivedUser = args.receivedUser

            val viewModelFactory = UserViewModelFactory(receivedUser as User)
            viewModel = ViewModelProvider(
                this@ReceiveUserFragment,
                viewModelFactory
            )[UserViewModel::class.java]
            userViewModel = viewModel

            //Allows the live data to automatically update the views
            lifecycleOwner = this@ReceiveUserFragment.viewLifecycleOwner

            viewModel.showSnackBarEvent.observe(viewLifecycleOwner, Observer {
                if (it == true) {
                    Snackbar.make(
                        activity!!.findViewById(android.R.id.content),
                        getString(R.string.message_undo),
                        Snackbar.LENGTH_SHORT // How long to display the message.
                    ).show()
                    viewModel.doneShowingSnackbar()
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
