package com.android.chandchand.data.common

interface Mapper<T, R> {
    fun map(item: T): R
}