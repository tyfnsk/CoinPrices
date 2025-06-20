package com.example.coinprices.presentation.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinprices.domain.repository.CoinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val repository: CoinRepository
) : ViewModel() {

    private val _state = MutableStateFlow(CoinDetailState())
    val state: StateFlow<CoinDetailState> = _state

    fun loadCoinDetail(coinId: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                val coin = repository.getCoins().firstOrNull { it.id == coinId }
                if (coin != null) {
                    _state.value = CoinDetailState(coin = coin)
                } else {
                    _state.value = CoinDetailState(error = "Coin not found")
                }
            } catch (e: Exception) {
                _state.value = CoinDetailState(error = e.localizedMessage ?: "An error occurred")
            }
        }
    }
}