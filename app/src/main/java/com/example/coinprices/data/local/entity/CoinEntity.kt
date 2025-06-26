package com.example.coinprices.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coins")
data class CoinEntity(
    @PrimaryKey val id: String,
    val name: String,
    val symbol: String,
    val image: String,
    val currentPrice: Double,
    val marketCapRank: Int,
    val priceChangePercentage: Double
)
