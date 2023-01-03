package com.anonymous.network.adapter

import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class ChandChandCallAdapter<R : Any>(
    private val successType: Type
) : CallAdapter<R, Call<Result<R>>> {

    override fun responseType(): Type = successType

    override fun adapt(call: Call<R>): Call<Result<R>> = ChandChandCall(call)
}