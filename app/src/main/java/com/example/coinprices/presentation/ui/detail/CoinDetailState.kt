package com.example.coinprices.presentation.ui.detail

import com.example.coinprices.domain.model.Coin

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: Coin? = null,
    val error: String = ""
)