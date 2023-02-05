package com.example.lukyanovtinkoff.app.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.lukyanovtinkoff.R
import com.example.lukyanovtinkoff.app.FilmsApplication
import com.example.lukyanovtinkoff.app.constants.FILM_ID_KEY
import com.example.lukyanovtinkoff.app.presentation.adapter.FilmAdapter
import com.example.lukyanovtinkoff.app.presentation.utils.RequestState
import com.example.lukyanovtinkoff.app.presentation.viewmodel.PopularViewModel
import com.example.lukyanovtinkoff.app.presentation.viewmodel.PopularViewModelFactory
import com.example.lukyanovtinkoff.databinding.FragmentPopularBinding
import javax.inject.Inject

class PopularFragment :
    BaseFragment<PopularViewModel, FragmentPopularBinding>(R.layout.fragment_popular) {

    @Inject
    lateinit var viewModelFactory: PopularViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity?.applicationContext as FilmsApplication).appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[PopularViewModel::class.java]
        _binding = FragmentPopularBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.popular)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            popularFragment = this@PopularFragment
            popularViewModel = viewModel
        }

        val filmAdapter = FilmAdapter(
            onClick = { filmId -> goToAbout(filmId) },
            onLongClick = { film -> viewModel.onLongClick(film) }
        )
        binding.filmsRecyclerView.adapter = filmAdapter

        viewModel.popularFilmsRequestState.collectRequestState(
            onError = { Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show() },
            onSuccess = { filmAdapter.submitList(it) },
            state = {
                binding.apply {
                    it.setupViewVisibility(filmsRecyclerView, loader, errorViewGroup)
                }
            }
        )
    }

    private fun goToAbout(filmId: Int) {
        val bundle = Bundle()
        bundle.putInt(FILM_ID_KEY, filmId)
        findNavController().navigate(R.id.action_popularFragment_to_aboutFragment, bundle)
    }

    fun goToFavourites() {
        findNavController().navigate(R.id.action_popularFragment_to_favouritesFragment)
    }
}