package com.anonymous.network.di

import com.anonymous.network.api.LeaguesApi
import com.anonymous.network.datasource.LeaguesDataSource
import com.anonymous.network.datasource.RemoteLeaguesDataSource
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
    fun provideLeaguesDataSource(
        remoteLeaguesDataSource: RemoteLeaguesDataSource
    ): LeaguesDataSource

    companion object {

        @Singleton
        @Provides
        fun provideLeaguesApi(retrofit: Retrofit): LeaguesApi {
            return retrofit.create(LeaguesApi::class.java)
        }
    }
}
