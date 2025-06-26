package com.example.coinprices.data.local.mapper

import com.example.coinprices.data.local.entity.CoinEntity
import com.example.coinprices.domain.model.Coin

fun CoinEntity.toDomain() = Coin(
    id, name, symbol, image, currentPrice, marketCapRank, priceChangePercentage
)