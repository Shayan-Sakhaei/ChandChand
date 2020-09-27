package com.android.chandchand.data.common

import com.android.chandchand.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .addHeader("x-rapidapi-key", BuildConfig.API_KEY)
            .build()

        return chain.proceed(request)
    }
}