package com.example.lukyanovtinkoff.app.presentation.view

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.lukyanovtinkoff.R
import com.example.lukyanovtinkoff.app.FilmsApplication
import com.example.lukyanovtinkoff.app.constants.FILM_ID_KEY
import com.example.lukyanovtinkoff.app.presentation.viewmodel.AboutViewModel
import com.example.lukyanovtinkoff.app.presentation.viewmodel.AboutViewModelFactory
import com.example.lukyanovtinkoff.databinding.FragmentAboutBinding
import com.example.lukyanovtinkoff.domain.model.Film
import javax.inject.Inject

class AboutFragment :
    BaseFragment<AboutViewModel, FragmentAboutBinding>(R.layout.fragment_about) {

    @Inject
    lateinit var viewModelFactory: AboutViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        hideActionBar()
        (activity?.applicationContext as FilmsApplication).appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[AboutViewModel::class.java]
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        arguments?.let {
            viewModel.setFilmId(it.getInt(FILM_ID_KEY))
            viewModel.getFilm()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            aboutFragment = this@AboutFragment
            aboutViewModel = viewModel
        }

        viewModel.getFilmRequestState.collectRequestState(
            onError = { Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show() },
            onSuccess = { bind(it) },
            state = { binding.apply { it.setupViewVisibility(content, loader, errorViewGroup) } }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        showActionBar()
    }

    private fun hideActionBar() {
//        activity?.let { WindowCompat.setDecorFitsSystemWindows(it.window, false) }
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    private fun showActionBar() {
//        activity?.let { WindowCompat.setDecorFitsSystemWindows(it.window, true) }
        (activity as AppCompatActivity).supportActionBar?.show()
    }

    private fun bind(film: Film) {
        binding.apply {
            titleTextView.text = film.name
            descriptionTextView.text = film.description
            genresTextView.text = film.genres.joinToString()
            countriesTextView.text = film.countries.joinToString()
            Glide.with(requireContext())
                .load(film.posterUrl)
                .listener(glideListener)
                .into(poster)
        }
    }

    private val glideListener = object : RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>?,
            isFirstResource: Boolean
        ): Boolean {
            Toast.makeText(
                requireContext(),
                e?.localizedMessage ?: getString(R.string.network_error),
                Toast.LENGTH_LONG
            ).show()
            binding.imgLoader.visibility = View.GONE
            return false
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            binding.imgLoader.visibility = View.GONE
            binding.poster.visibility = View.VISIBLE
            return false
        }

    }

    fun goBack() {
        findNavController().navigate(R.id.action_aboutFragment_to_popularFragment)
    }
}