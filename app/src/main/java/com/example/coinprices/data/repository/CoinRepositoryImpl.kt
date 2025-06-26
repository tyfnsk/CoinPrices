package com.example.coinprices.data.repository

import com.example.coinprices.data.local.dao.CoinDao
import com.example.coinprices.data.local.mapper.toDomain
import com.example.coinprices.data.remote.CoinApi
import com.example.coinprices.data.remote.dto.toCoin
import com.example.coinprices.data.remote.dto.toEntity
import com.example.coinprices.domain.model.Coin
import com.example.coinprices.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinApi,
    private val dao: CoinDao
): CoinRepository {

    override suspend fun getCoins(forceRefresh: Boolean): List<Coin> {
        val local = dao.getCoins()

        val shouldFetch = local.isEmpty() || forceRefresh
        if (shouldFetch) {
            try {
                val remote = api.getCoins()
                dao.clearCoins()
                dao.insertCoins(remote.map { it.toEntity() })
            } catch (e: Exception) {
                // if we get network error, we still return the local data
            }
        }
        return dao.getCoins().map { it.toDomain() }
    }
    }
