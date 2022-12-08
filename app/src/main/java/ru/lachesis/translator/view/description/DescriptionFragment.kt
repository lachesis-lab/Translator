package ru.lachesis.translator.view.description

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import coil.ImageLoader
import coil.request.LoadRequest
import ru.lachesis.translator.R
import ru.lachesis.translator.databinding.DescriptionFragmentBinding
import ru.lachesis.translator.model.data.DataModel
import ru.lachesis.translator.utils.AlertDialogFragment
import ru.lachesis.translator.utils.convertMeaningsToString
import ru.lachesis.translator.utils.network.isOnline
import ru.lachesis.translator.view.MainFragment

class DescriptionFragment() : Fragment() {
    private lateinit var binding: DescriptionFragmentBinding

    override fun onViewCreated(view: View, bundle: Bundle?) {
        super.onViewCreated(view, bundle)

        binding = DescriptionFragmentBinding.inflate(layoutInflater)

        //setActionbarHomeButtonAsUp()
        binding.descriptionScreenSwipeRefreshLayout.setOnRefreshListener { startLoadingOrShowError() }
        setData()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DescriptionFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

/*
    private fun setActionbarHomeButtonAsUp() {
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
*/

    private fun setData() {
        binding.descriptionHeader.text = arguments?.getString(WORD_EXTRA)
        binding.descriptionTextview.text = arguments?.getString(DESCRIPTION_EXTRA)
        val imageLink = arguments?.getString(URL_EXTRA)
        if (imageLink.isNullOrBlank()) {
            stopRefreshAnimationIfNeeded()
        } else {
            requireActivity().findViewById<ImageView>(R.id.description_imageview)
                ?.let { useCoilToLoadPhoto(it, imageLink) }
        }
    }

    private fun startLoadingOrShowError() {
        if (isOnline(requireContext())) {
            setData()
        } else {
            AlertDialogFragment.newInstance(
                getString(R.string.dialog_title_device_is_offline),
                getString(R.string.dialog_message_device_is_offline)
            ).show(
                childFragmentManager,
                DIALOG_FRAGMENT_TAG
            )
            stopRefreshAnimationIfNeeded()
        }
    }

    private fun stopRefreshAnimationIfNeeded() {
        if (binding.descriptionScreenSwipeRefreshLayout.isRefreshing) {
            binding.descriptionScreenSwipeRefreshLayout.isRefreshing = false
        }
    }

    private fun useCoilToLoadPhoto(imageView: ImageView, imageLink: String) {
        val request = LoadRequest.Builder(requireActivity())
            .data("https:$imageLink")
            .target(
                onStart = {},
                onSuccess = { result ->
                    imageView.setImageDrawable(result)
                },
                onError = {
                    imageView.setImageResource(R.drawable.ic_load_error_vector)
                }
            )
            //.transformations(
            //    CircleCropTransformation(),
            //)
            .build()

        ImageLoader(requireActivity()).execute(request)
    }

    companion object {

        private const val DIALOG_FRAGMENT_TAG = "8c7dff51-9769-4f6d-bbee-a3896085e76e"

        private const val WORD_EXTRA = "f76a288a-5dcc-43f1-ba89-7fe1d53f63b0"
        private const val DESCRIPTION_EXTRA = "0eeb92aa-520b-4fd1-bb4b-027fbf963d9a"
        private const val URL_EXTRA = "6e4b154d-e01f-4953-a404-639fb3bf7281"
        private var extras: Bundle? = null

        fun newInstance(data: DataModel): DescriptionFragment {
            val extras: Bundle? = Bundle()
            extras?.putString(WORD_EXTRA, data.text)
            extras?.putString(DESCRIPTION_EXTRA, convertMeaningsToString(data.meanings!!))
            extras?.putString(URL_EXTRA, data.meanings?.get(0)?.imageUrl)
            val fragment = DescriptionFragment()
            fragment.arguments = extras
            return fragment
        }
    }
}
/*      fun getIntent(
          context: Context,
          word: String,
          description: String,
          url: String?
      ): Intent = Intent(context, DescriptionActivity::class.java).apply {
          putExtra(WORD_EXTRA, word)
          putExtra(DESCRIPTION_EXTRA, description)
          putExtra(URL_EXTRA, url)
      }
  }
}*/
