package com.example.coinprices.domain.model

data class Coin(
    val id:String,
    val name:String,
    val symbol:String,
    val image:String,
    val currentPrice:Double,
    val marketCapRank:Int,
    val priceChangePercentage:Double
)
