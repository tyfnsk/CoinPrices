package com.example.coinprices.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.coinprices.data.local.entity.CoinEntity

@Dao
interface CoinDao {

    @Query("SELECT * FROM coins")
    suspend fun getCoins(): List<CoinEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoins(coins: List<CoinEntity>)

    @Query("DELETE FROM coins")
    suspend fun clearCoins()
}