package com.android.chandchand.presentation.di.leagues

import com.android.chandchand.data.leagues.api.LeaguesApi
import com.android.chandchand.data.leagues.datasource.RemoteLeaguesDataSource
import com.android.chandchand.data.leagues.repository.LeaguesRepositoryImpl
import com.android.chandchand.domain.datasources.LeaguesDataSource
import com.android.domain.repositories.LeaguesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface LeaguesModule {

    @Singleton
    @Binds
    fun provideDataSource(
        remoteLeaguesDataSource: RemoteLeaguesDataSource
    ): LeaguesDataSource

    @Singleton
    @Binds
    fun provideRepository(
        leaguesRepositoryImpl: LeaguesRepositoryImpl
    ): com.android.domain.repositories.LeaguesRepository

    companion object {

        @Singleton
        @Provides
        fun provideLeaguesApi(retrofit: Retrofit): LeaguesApi {
            return retrofit.create(LeaguesApi::class.java)
        }
    }
}
