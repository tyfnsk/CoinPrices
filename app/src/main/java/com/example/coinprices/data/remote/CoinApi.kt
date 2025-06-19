package com.example.coinprices.data.remote

import com.example.coinprices.data.remote.dto.CoinDto
import retrofit2.http.GET

interface CoinApi {

    @GET("coins/markets?vs_currency=usd")
    suspend fun getCoins(): List<CoinDto>
}