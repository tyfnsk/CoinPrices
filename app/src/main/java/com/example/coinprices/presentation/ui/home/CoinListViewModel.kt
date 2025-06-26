package com.example.coinprices.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinprices.domain.use_case.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CoinListState())
    val state: StateFlow<CoinListState> = _state

    init {
        loadCoins()                   // when first loading room is empty so load from api
    }

    /** call swipe refresh from UI */
    fun refresh() = loadCoins(forceRefresh = true)

    /** forceRefresh=true ⇒ repull from api refresh to room */
    private fun loadCoins(forceRefresh: Boolean = false) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = "") }

            try {
                val list = getCoinsUseCase(forceRefresh)
                _state.value = CoinListState(coins = list)   // loading=false
            } catch (e: Exception) {
                _state.value = CoinListState(
                    error = e.localizedMessage ?: "Unknown error"
                )
            }
        }
    }
}