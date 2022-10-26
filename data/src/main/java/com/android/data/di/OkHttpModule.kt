package com.android.data.di

import android.content.Context
import android.util.Log
import com.android.data.common.AuthInterceptor
import com.squareup.moshi.Moshi
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

const val BASE_URL = "https://v2.api-football.com/"

@Module
@InstallIn(SingletonComponent::class)
object OkHttpModule {

    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.d("network", message)
            }
        })

        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return httpLoggingInterceptor
    }

    @Provides
    fun provideHttpCache(@ApplicationContext context: Context): Cache =
        Cache(context.cacheDir, 10 * 1024 * 1024)

    @Provides
    @Singleton
    fun provideClient(
        loggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor,
        cache: Cache
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .cache(cache)
            .build()
    }

    @Provides
    fun provideRetrofit(moshi: Moshi, client: Lazy<OkHttpClient>): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .callFactory(object : Call.Factory {
                override fun newCall(request: Request): Call {
                    return client.get().newCall(request)
                }
            })
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }
}