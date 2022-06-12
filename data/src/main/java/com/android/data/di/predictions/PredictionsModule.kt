package com.android.data.di.predictions

import com.android.data.datasources.PredictionsDataSource
import com.android.data.predictions.api.PredictionsApi
import com.android.data.predictions.datasource.RemotePredictionsDataSource
import com.android.data.predictions.repository.PredictionsRepositoryImpl
import com.android.domain.repositories.PredictionsRepository
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
    fun provideDataSource(
        remotePredictionsDataSource: RemotePredictionsDataSource
    ): PredictionsDataSource

    @Singleton
    @Binds
    fun provideRepository(
        predictionsRepositoryImpl: PredictionsRepositoryImpl
    ): PredictionsRepository

    companion object {

        @Singleton
        @Provides
        fun providePredictionsApi(retrofit: Retrofit): PredictionsApi {
            return retrofit.create(PredictionsApi::class.java)
        }
    }
}