package com.example.lukyanovtinkoff.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AboutViewModel : BaseViewModel() {
}

@Suppress("UNCHECKED_CAST")
class AboutViewModelFactory(
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AboutViewModel::class.java)) {
            return AboutViewModel(

            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}