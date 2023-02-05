package com.example.lukyanovtinkoff.app.presentation.viewmodel

import androidx.lifecycle.*
import com.example.lukyanovtinkoff.domain.model.Film
import com.example.lukyanovtinkoff.domain.usecase.DeleteFilmUseCase
import com.example.lukyanovtinkoff.domain.usecase.GetFavouriteFilmsUseCase
import com.example.lukyanovtinkoff.domain.usecase.SaveFilmUseCase
import kotlinx.coroutines.launch

class FavouritesViewModel(
    getFavouriteFilmsUseCase: GetFavouriteFilmsUseCase,
    private val saveFilmUseCase: SaveFilmUseCase,
    private val deleteFilmUseCase: DeleteFilmUseCase
): ViewModel() {

    val favouriteFilms: LiveData<List<Film>> = getFavouriteFilmsUseCase.invoke().asLiveData()

    fun onLongClick(film: Film) = viewModelScope.launch {
        if (film.favourite)
            deleteFilmUseCase.invoke(film)
        else
            saveFilmUseCase.invoke(film)
    }
}

@Suppress("UNCHECKED_CAST")
class FavouritesViewModelFactory(
    private val getFavouriteFilmsUseCase: GetFavouriteFilmsUseCase,
    private val saveFilmUseCase: SaveFilmUseCase,
    private val deleteFilmUseCase: DeleteFilmUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavouritesViewModel::class.java)) {
            return FavouritesViewModel(
                getFavouriteFilmsUseCase = getFavouriteFilmsUseCase,
                saveFilmUseCase = saveFilmUseCase,
                deleteFilmUseCase = deleteFilmUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}