package com.android.chandchand.presentation.di.predictions

import com.android.chandchand.data.predictions.api.PredictionsApi
import com.android.chandchand.data.predictions.datasource.RemotePredictionsDataSource
import com.android.chandchand.data.predictions.repository.PredictionsRepositoryImpl
import com.android.chandchand.domain.datasources.PredictionsDataSource
import com.android.chandchand.domain.repositories.PredictionsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
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