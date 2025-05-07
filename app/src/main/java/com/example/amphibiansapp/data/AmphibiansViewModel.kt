package com.example.amphibiansapp.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.amphibiansapp.model.Amphibian
import kotlinx.coroutines.launch

class AmphibiansViewModel(private val repository: AmphibianRepository) : ViewModel() {
    private val _amphibians = MutableLiveData<List<Amphibian>>(emptyList())
    val amphibians: LiveData<List<Amphibian>> = _amphibians

    init {
        viewModelScope.launch {
            _amphibians.value = try {
                repository.getAmphibians()
            } catch (e: Exception) {
                emptyList()
            }
        }
    }
}
