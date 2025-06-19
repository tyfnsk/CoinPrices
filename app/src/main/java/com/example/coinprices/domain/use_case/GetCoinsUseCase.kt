package com.example.coinprices.domain.use_case

import com.example.coinprices.domain.model.Coin
import com.example.coinprices.domain.repository.CoinRepository
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(private val repository: CoinRepository) {
    suspend operator fun invoke(): List<Coin>{
        return repository.getCoins()
    }
}