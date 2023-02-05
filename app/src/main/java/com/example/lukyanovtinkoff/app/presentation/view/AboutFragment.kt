package com.example.lukyanovtinkoff.app.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.lukyanovtinkoff.R
import com.example.lukyanovtinkoff.app.FilmsApplication
import com.example.lukyanovtinkoff.app.constants.FILM_ID_KEY
import com.example.lukyanovtinkoff.app.presentation.viewmodel.AboutViewModel
import com.example.lukyanovtinkoff.app.presentation.viewmodel.AboutViewModelFactory
import com.example.lukyanovtinkoff.databinding.FragmentAboutBinding
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
        (activity?.applicationContext as FilmsApplication).appComponent.inject(this)
        activity?.let { WindowCompat.setDecorFitsSystemWindows(it.window, false) }
        viewModel = ViewModelProvider(this, viewModelFactory)[AboutViewModel::class.java]
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        arguments?.let { viewModel.getFilm(it.getInt(FILM_ID_KEY)) }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.aboutFragment = this@AboutFragment

        viewModel.getFilmRequestState.collectRequestState(
            onError = { Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
                      Log.d("TAG", it)},
            onSuccess = {
                binding.apply {
                    titleTextView.text = it.name
                    descriptionTextView.text = it.description
                    genresTextView.text = it.genres.joinToString()
                    countriesTextView.text = it.countries.joinToString()
                    Glide.with(requireContext())
                        .load(it.posterUrl)
                        .into(poster)
                }
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        activity?.let { WindowCompat.setDecorFitsSystemWindows(it.window, true) }
    }

    fun goBack() {
        findNavController().navigate(R.id.action_aboutFragment_to_popularFragment)
    }
}