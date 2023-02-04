package com.example.lukyanovtinkoff.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lukyanovtinkoff.app.presentation.utils.RequestState
import com.example.lukyanovtinkoff.domain.utils.Either
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
    /**
     * Collect network request and return [RequestState] depending on request result
     */
    fun <T> Flow<Either<String, T>>.collectRequest(
        state: MutableStateFlow<RequestState<T>>,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            state.value = RequestState.Loading()
            this@collectRequest.collect {
                when (it) {
                    is Either.Left -> state.value = RequestState.Error(it.value)
                    is Either.Right -> state.value = RequestState.Success(it.value)
                }
            }
        }
    }
}