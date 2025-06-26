package com.example.coinprices.di

import com.example.coinprices.core.util.API_URL
import com.example.coinprices.data.local.dao.CoinDao
import com.example.coinprices.data.remote.CoinApi
import com.example.coinprices.data.repository.CoinRepositoryImpl
import com.example.coinprices.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCoinApi(): CoinApi {
        return Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: CoinApi, dao: CoinDao): CoinRepository {
        return CoinRepositoryImpl(api, dao)
    }


}