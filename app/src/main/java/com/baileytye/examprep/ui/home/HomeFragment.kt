package com.baileytye.examprep.ui.home


import android.app.Activity.RESULT_OK
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.ParcelFileDescriptor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.baileytye.examprep.R
import com.baileytye.examprep.data.User
import com.baileytye.examprep.databinding.FragmentHomeBinding
import com.baileytye.examprep.util.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*


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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        println("DEBUG: Result with request code: $requestCode")
        when (requestCode) {
            RC_PDF -> {
                if (resultCode == RESULT_OK) {
                    CoroutineScope(Dispatchers.Default).launch {
                        data?.data?.let {
                            splicePdf(it)
                        }
                    }
                }
            }
            else -> {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }

    private suspend fun splicePdf(uri: Uri) {
        val file = createLocalFile()
        println("DEBUG: filepath: ${file.path}")
        copyUriToFile(uri, file)

        val renderer = PdfRenderer(
            ParcelFileDescriptor.open(
                file,
                ParcelFileDescriptor.MODE_READ_ONLY
            )
        )
        val pageCount = renderer.pageCount
        var bitmap: Bitmap
        val bitmaps = arrayListOf<Bitmap>()

        for (i in 0 until pageCount) {
            val page = renderer.openPage(i)
            val width =
                resources.displayMetrics.densityDpi / 72 * page.width
            val height =
                resources.displayMetrics.densityDpi / 72 * page.height
            bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
            bitmaps.add(bitmap)
            // close the page
            page.close()
        }

        renderer.close()
        println("DEBUG: Pages in pdf: ${bitmaps.size}")
        withContext(Dispatchers.Main) {
            Toast.makeText(
                context,
                "Pages in pdf: ${bitmaps.size}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun copyUriToFile(uri: Uri, outFile: File) {
        val inputStream = requireActivity().contentResolver.openInputStream(uri)
        val outputStream = FileOutputStream(outFile)
        val buf = ByteArray(1024)
        var len: Int = inputStream?.read(buf) ?: 0
        while (len > 0) {
            outputStream.write(buf, 0, len)
            len = inputStream?.read(buf) ?: 0
        }
        outputStream.close()
        inputStream?.close()
    }

    private fun createLocalFile(): File {

        val timeStamp = SimpleDateFormat("ddMMyyyy_HHmmss", Locale.ENGLISH).format(Date())
        val pdfName = "PDF_${timeStamp}_"
        val storageDir = requireActivity().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        return File.createTempFile(pdfName, ".pdf", storageDir)
    }

    override fun onStartPDF() {
        val intent = Intent()
        intent.type = "application/pdf"
        intent.action = Intent.ACTION_GET_CONTENT
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        startActivityForResult(Intent.createChooser(intent, "Select PDF"), RC_PDF)
    }

    override fun onStartMirrorText() {
        this.hideKeyboard()
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToMirrorTextFragment())
    }

    override fun onStartReceiveUser() {
        this.hideKeyboard()

        val user = User(
            binding.editTextFirstName.text.toString(),
            binding.editTextLastName.text.toString()
        )
        //This can be moved to viewmodel by having reference to app in there. Should be injected by dagger
        this.activity?.applicationContext?.let {
            val notificationManager = requireActivity().getSystemService(
                NotificationManager::class.java
            ) as NotificationManager

            //Not sure how to get the name of argument without specifying it
            notificationManager.sendNotification(
                binding.editTextFirstName.text.toString(),
                it,
                Bundle().apply { putParcelable("receivedUser", user) })
        }

        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToReceiveTextFragment(user)
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

    override fun onStartValidation() {
        hideKeyboard()
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToValidationFragment())
    }

    override fun onStartCanvas() {
        hideKeyboard()
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToPaintFragment())
    }

    override fun onStartPager() {
        hideKeyboard()
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToPagerFragment())
    }
}
