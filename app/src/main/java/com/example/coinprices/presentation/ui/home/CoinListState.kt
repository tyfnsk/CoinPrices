package com.example.coinprices.presentation.ui.home

import com.example.coinprices.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)