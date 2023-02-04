package com.example.lukyanovtinkoff.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lukyanovtinkoff.app.presentation.utils.RequestState
import com.example.lukyanovtinkoff.domain.model.Film
import com.example.lukyanovtinkoff.domain.usecase.GetPopularFilmsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PopularViewModel(
    private val getPopularFilmsUseCase: GetPopularFilmsUseCase
) : BaseViewModel() {

    private val _popularFilmsRequestState =
        MutableStateFlow<RequestState<List<Film>>>(RequestState.Idle())
    val popularFilmsRequestState = _popularFilmsRequestState.asStateFlow()

    fun getPopularFilms() =
        getPopularFilmsUseCase.invoke().collectRequest(_popularFilmsRequestState)
}

@Suppress("UNCHECKED_CAST")
class PopularViewModelFactory(
    private val getPopularFilmsUseCase: GetPopularFilmsUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PopularViewModel::class.java)) {
            return PopularViewModel(
                getPopularFilmsUseCase = getPopularFilmsUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}