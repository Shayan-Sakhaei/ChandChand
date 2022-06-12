package com.android.domain.common

interface Mapper<T, R> {
    fun map(item: T): R
}