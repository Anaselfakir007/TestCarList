package com.carlist.capgemini

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlist.capgemini.data.CarDataModel
import com.carlist.capgemini.usecase.CarsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class CarsViewModel(private val getCarsUseCase: CarsUseCase) : ViewModel() {
        private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
        val uiState: StateFlow<UiState> = _uiState

    init {
        fetchCars(CarsActivity.model)
    }
        fun fetchCars(make: String) {
            viewModelScope.launch {
                _uiState.value = UiState.Loading
                try {
                    val cars = getCarsUseCase(make)
                    _uiState.value = UiState.Success(cars)
                } catch (e: Exception) {
                    _uiState.value = UiState.Error(R.string.error_message)
                }
            }
        }
    }

sealed class UiState {
    data object Loading : UiState()
    data class Success(val cars: List<CarDataModel>) : UiState()
    data class Error(val message: Int) : UiState()
}