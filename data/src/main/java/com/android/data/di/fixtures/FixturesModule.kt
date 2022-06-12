package com.android.data.di.fixtures

import com.android.data.datasources.FixturesDataSource
import com.android.data.fixtures.api.FixturesApi
import com.android.data.fixtures.datasource.RemoteFixturesDataSource
import com.android.data.fixtures.repository.FixturesRepositoryImpl
import com.android.domain.repositories.FixturesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface FixturesModule {

    @Singleton
    @Binds
    fun provideDataSource(
        remoteFixturesDataSource: RemoteFixturesDataSource
    ): FixturesDataSource

    @Singleton
    @Binds
    fun provideRepository(
        leaguesRepositoryImpl: FixturesRepositoryImpl
    ): FixturesRepository

    companion object {

        @Singleton
        @Provides
        fun provideFixturesApi(retrofit: Retrofit): FixturesApi {
            return retrofit.create(FixturesApi::class.java)
        }
    }
}
