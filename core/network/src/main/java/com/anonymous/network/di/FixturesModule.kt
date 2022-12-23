package com.anonymous.network.di

import com.anonymous.network.api.FixturesApi
import com.anonymous.network.datasource.FixturesDataSource
import com.anonymous.network.datasource.RemoteFixturesDataSource
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
    fun provideFixturesDataSource(
        remoteFixturesDataSource: RemoteFixturesDataSource
    ): FixturesDataSource

    companion object {

        @Singleton
        @Provides
        fun provideFixturesApi(retrofit: Retrofit): FixturesApi {
            return retrofit.create(FixturesApi::class.java)
        }
    }
}
