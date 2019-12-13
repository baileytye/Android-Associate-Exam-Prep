package com.baileytye.examprep.ui


import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.baileytye.examprep.R
import com.baileytye.examprep.data.User
import com.baileytye.examprep.databinding.FragmentReceiveUserBinding

/**
 * A simple [Fragment] subclass.
 */
class ReceiveUserFragment : Fragment() {
    lateinit var binding: FragmentReceiveUserBinding
    val args: ReceiveUserFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_receive_user, container, false
        )
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.apply {
            receivedUser = args.receivedUser
            buttonChangeUser.setOnClickListener {
                receivedUser = User("Changed 1", "Changed 2")
            }
        }
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
