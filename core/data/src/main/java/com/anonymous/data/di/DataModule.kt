package com.anonymous.data.di

import com.anonymous.data.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun provideFixturesRepository(
        fixturesRepositoryImpl: FixturesRepositoryImpl
    ): FixturesRepository

    @Binds
    fun provideLeaguesRepository(
        leaguesRepositoryImpl: LeaguesRepositoryImpl
    ): LeaguesRepository

    @Binds
    fun providePredictionsRepository(
        predictionsRepositoryImpl: PredictionsRepositoryImpl
    ): PredictionsRepository
}