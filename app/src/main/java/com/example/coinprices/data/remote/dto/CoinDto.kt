package com.example.coinprices.data.remote.dto

import com.example.coinprices.domain.model.Coin
import com.google.gson.annotations.SerializedName

data class CoinDto(
    val id:String,
    val symbol:String,
    val name:String,
    val image:String,

    @SerializedName("current_price")
    val currentPrice:Double,

    @SerializedName("market_cap_rank")
    val marketCapRank:Int
)


fun CoinDto.toCoin(): Coin {
    return Coin(
        id=id,
        name=name,
        symbol=symbol,
        image=image,
        currentPrice=currentPrice,
        marketCapRank = marketCapRank
    )
}
