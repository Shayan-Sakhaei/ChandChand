package com.android.chandchand.presentation.di.leagues

import com.android.chandchand.data.leagues.api.LeaguesApi
import com.android.chandchand.data.leagues.datasource.RemoteLeaguesDataSource
import com.android.chandchand.data.leagues.repository.LeaguesRepositoryImpl
import com.android.chandchand.domain.datasources.LeaguesDataSource
import com.android.chandchand.domain.repositories.LeaguesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
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
