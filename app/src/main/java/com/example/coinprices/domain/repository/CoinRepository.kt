package com.example.coinprices.domain.repository

import com.example.coinprices.domain.model.Coin

interface CoinRepository {

    suspend fun getCoins():List<Coin>

}