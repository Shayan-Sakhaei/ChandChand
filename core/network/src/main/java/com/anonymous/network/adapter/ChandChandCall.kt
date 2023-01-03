package com.anonymous.network.adapter

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChandChandCall<R : Any>(
    private val delegate: Call<R>
) : Call<Result<R>> {

    override fun clone(): Call<Result<R>> = ChandChandCall(delegate.clone())

    override fun execute(): Response<Result<R>> {
        throw UnsupportedOperationException("ChandChandCall doesn't support execute!")
    }

    override fun enqueue(callback: Callback<Result<R>>) {
        delegate.enqueue(object : Callback<R> {

            override fun onResponse(call: Call<R>, response: Response<R>) {
                callback.onResponse(
                    this@ChandChandCall,
                    Response.success(response.toResult())
                )
            }

            override fun onFailure(call: Call<R>, t: Throwable) {
                callback.onResponse(
                    this@ChandChandCall,
                    Response.success(Result.failure(t))
                )
            }
        })
    }

    override fun isExecuted(): Boolean = delegate.isExecuted

    override fun cancel() = delegate.cancel()

    override fun isCanceled(): Boolean = delegate.isCanceled

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()

    private fun Response<R>.toResult(): Result<R> {
        val body = this.body()
        return if (!this.isSuccessful || body == null) {
            Result.failure(Throwable())
        } else {
            Result.success(body)
        }
    }
}