package com.example.lukyanovtinkoff.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.lukyanovtinkoff.app.presentation.utils.RequestState
import com.example.lukyanovtinkoff.domain.model.Film
import com.example.lukyanovtinkoff.domain.usecase.GetPopularFilmsUseCase
import com.example.lukyanovtinkoff.domain.usecase.SaveFilmUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PopularViewModel(
    getPopularFilmsUseCase: GetPopularFilmsUseCase,
    private val saveFilmUseCase: SaveFilmUseCase
) : BaseViewModel() {

    private val _popularFilmsRequestState =
        MutableStateFlow<RequestState<List<Film>>>(RequestState.Idle())
    val popularFilmsRequestState = _popularFilmsRequestState.asStateFlow()

    init {
        getPopularFilmsUseCase.invoke().collectRequest(_popularFilmsRequestState)
    }

    fun saveFilm(film: Film) = viewModelScope.launch {
        saveFilmUseCase.invoke(film)
    }
}

@Suppress("UNCHECKED_CAST")
class PopularViewModelFactory(
    private val getPopularFilmsUseCase: GetPopularFilmsUseCase,
    private val saveFilmUseCase: SaveFilmUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PopularViewModel::class.java)) {
            return PopularViewModel(
                getPopularFilmsUseCase = getPopularFilmsUseCase,
                saveFilmUseCase = saveFilmUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}