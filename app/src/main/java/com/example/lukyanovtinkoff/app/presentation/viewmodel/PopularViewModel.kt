package com.example.lukyanovtinkoff.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.lukyanovtinkoff.app.presentation.utils.RequestState
import com.example.lukyanovtinkoff.domain.model.Film
import com.example.lukyanovtinkoff.domain.usecase.DeleteFilmUseCase
import com.example.lukyanovtinkoff.domain.usecase.GetPopularFilmsUseCase
import com.example.lukyanovtinkoff.domain.usecase.SaveFilmUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PopularViewModel(
    getPopularFilmsUseCase: GetPopularFilmsUseCase,
    private val saveFilmUseCase: SaveFilmUseCase,
    private val deleteFilmUseCase: DeleteFilmUseCase
) : BaseViewModel() {

    private val _popularFilmsRequestState =
        MutableStateFlow<RequestState<List<Film>>>(RequestState.Idle())
    val popularFilmsRequestState = _popularFilmsRequestState.asStateFlow()

    init {
        getPopularFilmsUseCase.invoke().collectRequest(_popularFilmsRequestState)
    }

    fun onLongClick(film: Film) = viewModelScope.launch {
        if (film.favourite)
            deleteFilmUseCase.invoke(film)
        else
            saveFilmUseCase.invoke(film)
    }
}

@Suppress("UNCHECKED_CAST")
class PopularViewModelFactory(
    private val getPopularFilmsUseCase: GetPopularFilmsUseCase,
    private val saveFilmUseCase: SaveFilmUseCase,
    private val deleteFilmUseCase: DeleteFilmUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PopularViewModel::class.java)) {
            return PopularViewModel(
                getPopularFilmsUseCase = getPopularFilmsUseCase,
                saveFilmUseCase = saveFilmUseCase,
                deleteFilmUseCase = deleteFilmUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}