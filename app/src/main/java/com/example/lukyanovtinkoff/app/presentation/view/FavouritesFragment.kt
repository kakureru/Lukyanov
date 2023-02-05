package com.example.lukyanovtinkoff.app.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
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
    private lateinit var filmAdapter: FilmAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity?.applicationContext as FilmsApplication).appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[FavouritesViewModel::class.java]
        _binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.favourites)
        (requireActivity() as MenuHost).addMenuProvider(menuProvider, viewLifecycleOwner)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.favouritesFragment = this

        filmAdapter = FilmAdapter(
            onClick = { filmId -> goToAbout(filmId) },
            onLongClick = { film -> viewModel.onLongClick(film) }
        )
        binding.filmsRecyclerView.adapter = filmAdapter

        viewModel.favouriteFilms.observe(viewLifecycleOwner) {
            filmAdapter.addData(it)
//            filmAdapter.submitList(it)
        }
    }

    private val menuProvider = object : MenuProvider {
        override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
            menuInflater.inflate(R.menu.action_bar, menu)
            val searchAction = menu.findItem(R.id.action_search)
            val searchView = searchAction?.actionView as SearchView

            val searchAutoComplete: SearchView.SearchAutoComplete =
                searchView.findViewById(androidx.appcompat.R.id.search_src_text)
            searchAutoComplete.setHintTextColor(resources.getColor(R.color.half_black))

            searchView.setOnQueryTextListener(onQueryTextListener)
        }

        override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
            return false
        }

    }

    private val onQueryTextListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(p0: String?): Boolean {
            return false
        }

        override fun onQueryTextChange(text: String?): Boolean {
            filmAdapter.filter.filter(text)
            return false
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