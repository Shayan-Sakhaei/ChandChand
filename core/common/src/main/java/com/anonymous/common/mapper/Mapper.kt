package com.anonymous.common.mapper

interface Mapper<T, R> {
    fun map(item: T): R
}