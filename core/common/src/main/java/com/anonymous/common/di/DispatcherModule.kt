package com.anonymous.common.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

    @MainDispatcher
    @Provides
    fun provideMainDispatcher(): CoroutineDispatcher =
        Dispatchers.Main

    @IoDispatcher
    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher =
        Dispatchers.IO

    @DefaultDispatcher
    @Provides
    fun provideDefaultDispatcher(): CoroutineDispatcher =
        Dispatchers.Default
}