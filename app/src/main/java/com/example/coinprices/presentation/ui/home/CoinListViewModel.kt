package com.example.coinprices.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinprices.domain.use_case.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CoinListState())
    val state: StateFlow<CoinListState> = _state

    init {
        loadCoins()
    }

    private fun loadCoins() {
        viewModelScope.launch {
            _state.value = CoinListState(isLoading = true)
            try {
                val coins = getCoinsUseCase()
                _state.value = CoinListState(coins = coins)
            } catch (e: Exception) {
                _state.value = CoinListState(error = e.message ?: "Unknown error")
            }
        }
    }
}