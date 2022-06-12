package com.android.data.di.leagues

import com.android.data.datasources.LeaguesDataSource
import com.android.data.leagues.api.LeaguesApi
import com.android.data.leagues.datasource.RemoteLeaguesDataSource
import com.android.data.leagues.repository.LeaguesRepositoryImpl
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
    ): LeaguesRepository

    companion object {

        @Singleton
        @Provides
        fun provideLeaguesApi(retrofit: Retrofit): LeaguesApi {
            return retrofit.create(LeaguesApi::class.java)
        }
    }
}
