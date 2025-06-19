package com.example.coinprices.data.repository

import com.example.coinprices.data.remote.CoinApi
import com.example.coinprices.data.remote.dto.toCoin
import com.example.coinprices.domain.model.Coin
import com.example.coinprices.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinApi
): CoinRepository {
    override suspend fun getCoins(): List<Coin> {
      return  api.getCoins().map { it.toCoin() }
    }
}