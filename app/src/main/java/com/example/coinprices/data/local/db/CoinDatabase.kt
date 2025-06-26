package com.example.coinprices.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.coinprices.data.local.dao.CoinDao
import com.example.coinprices.data.local.entity.CoinEntity

@Database(entities = [CoinEntity::class], version = 1)
abstract class CoinDatabase : RoomDatabase() {
    abstract fun coinDao(): CoinDao
}