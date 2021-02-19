package com.android.chandchand.presentation.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ApplicationComponent::class)
object SharedPrefModule {

    @Provides
    fun provideDefaultSharedPref(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences("default_preferences", Context.MODE_PRIVATE)

}