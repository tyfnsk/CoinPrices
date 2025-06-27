package com.example.coinprices.domain.use_case

import android.util.Log
import com.example.coinprices.domain.model.Coin
import com.example.coinprices.domain.repository.CoinRepository
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(private val repository: CoinRepository) {
    suspend operator fun invoke(forceRefresh: Boolean = false): List<Coin>{
        return repository.getCoins(forceRefresh)
    }
}