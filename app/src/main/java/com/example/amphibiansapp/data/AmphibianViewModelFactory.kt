package com.example.amphibiansapp.data


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AmphibiansViewModelFactory(
    private val repository: AmphibianRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AmphibiansViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AmphibiansViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}