package com.android.chandchand.presentation.di.fixtures

import com.android.chandchand.data.fixtures.api.FixturesApi
import com.android.chandchand.data.fixtures.datasource.RemoteFixturesDataSource
import com.android.chandchand.data.fixtures.repository.FixturesRepositoryImpl
import com.android.chandchand.domain.datasources.FixturesDataSource
import com.android.chandchand.domain.repositories.FixturesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
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
