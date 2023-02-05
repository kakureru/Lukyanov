package com.example.lukyanovtinkoff.app.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.lukyanovtinkoff.R
import com.example.lukyanovtinkoff.app.FilmsApplication
import com.example.lukyanovtinkoff.app.constants.FILM_ID_KEY
import com.example.lukyanovtinkoff.app.presentation.adapter.FilmAdapter
import com.example.lukyanovtinkoff.app.presentation.viewmodel.FavouritesViewModel
import com.example.lukyanovtinkoff.app.presentation.viewmodel.FavouritesViewModelFactory
import com.example.lukyanovtinkoff.databinding.FragmentFavouritesBinding
import javax.inject.Inject

class FavouritesFragment :
    BaseFragment<FavouritesViewModel, FragmentFavouritesBinding>(R.layout.fragment_favourites) {

    @Inject
    lateinit var viewModelFactory: FavouritesViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity?.applicationContext as FilmsApplication).appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[FavouritesViewModel::class.java]
        _binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.favourites)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.favouritesFragment = this

        val filmAdapter = FilmAdapter(
            onClick = { filmId -> goToAbout(filmId) },
            onLongClick = { film -> viewModel.onLongClick(film) }
        )
        binding.filmsRecyclerView.adapter = filmAdapter

        viewModel.favouriteFilms.observe(viewLifecycleOwner) {
            filmAdapter.submitList(it)
        }
    }

    private fun goToAbout(filmId: Int) {
        val bundle = Bundle()
        bundle.putInt(FILM_ID_KEY, filmId)
        findNavController().navigate(R.id.action_favouritesFragment_to_aboutFragment, bundle)
    }

    fun goToPopular() {
        findNavController().navigate(R.id.action_favouritesFragment_to_popularFragment)
    }
}