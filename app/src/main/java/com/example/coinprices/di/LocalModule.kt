package com.example.coinprices.di

import android.app.Application
import androidx.room.Room
import com.example.coinprices.data.local.dao.CoinDao
import com.example.coinprices.data.local.db.CoinDatabase
import com.example.coinprices.data.remote.CoinApi
import com.example.coinprices.data.repository.CoinRepositoryImpl
import com.example.coinprices.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideDb(app: Application) =
        Room.databaseBuilder(app, CoinDatabase::class.java, "coin_db").build()

    @Provides fun provideDao(db: CoinDatabase) = db.coinDao()

}