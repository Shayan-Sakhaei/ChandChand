package com.anonymous.network.di

import com.anonymous.network.api.PredictionsApi
import com.anonymous.network.datasource.PredictionsDataSource
import com.anonymous.network.datasource.RemotePredictionsDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface PredictionsModule {

    @Singleton
    @Binds
    fun providePredictionsDataSource(
        remotePredictionsDataSource: RemotePredictionsDataSource
    ): PredictionsDataSource

    companion object {

        @Singleton
        @Provides
        fun providePredictionsApi(retrofit: Retrofit): PredictionsApi {
            return retrofit.create(PredictionsApi::class.java)
        }
    }
}