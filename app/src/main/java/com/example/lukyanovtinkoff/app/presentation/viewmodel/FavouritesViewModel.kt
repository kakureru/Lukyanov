package com.example.lukyanovtinkoff.app.presentation.viewmodel

import androidx.lifecycle.*
import com.example.lukyanovtinkoff.domain.model.Film
import com.example.lukyanovtinkoff.domain.usecase.GetFavouriteFilmsUseCase
import kotlinx.coroutines.launch

class FavouritesViewModel(
    getFavouriteFilmsUseCase: GetFavouriteFilmsUseCase
): ViewModel() {

    val favouriteFilms: LiveData<List<Film>> = getFavouriteFilmsUseCase.invoke().asLiveData()

    fun removeFromFavourites(film: Film) = viewModelScope.launch {

    }
}

@Suppress("UNCHECKED_CAST")
class FavouritesViewModelFactory(
    private val getFavouriteFilmsUseCase: GetFavouriteFilmsUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavouritesViewModel::class.java)) {
            return FavouritesViewModel(
                getFavouriteFilmsUseCase = getFavouriteFilmsUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}