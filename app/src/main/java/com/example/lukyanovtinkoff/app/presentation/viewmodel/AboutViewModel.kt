package com.example.lukyanovtinkoff.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lukyanovtinkoff.app.presentation.utils.RequestState
import com.example.lukyanovtinkoff.domain.model.Film
import com.example.lukyanovtinkoff.domain.usecase.GetFilmUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AboutViewModel(
    private val getFilmUseCase: GetFilmUseCase
) : BaseViewModel() {

    private val _getFilmRequestState = MutableStateFlow<RequestState<Film>>(RequestState.Idle())
    val getFilmRequestState = _getFilmRequestState.asStateFlow()

    fun getFilm(filmId: Int) = getFilmUseCase.invoke(filmId).collectRequest(_getFilmRequestState)
}

@Suppress("UNCHECKED_CAST")
class AboutViewModelFactory(
    private val getFilmUseCase: GetFilmUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AboutViewModel::class.java)) {
            return AboutViewModel(
                getFilmUseCase = getFilmUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}