package com.example.lukyanovtinkoff.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FavouritesViewModel: ViewModel() {
}

@Suppress("UNCHECKED_CAST")
class FavouritesViewModelFactory(
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavouritesViewModel::class.java)) {
            return FavouritesViewModel(
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}