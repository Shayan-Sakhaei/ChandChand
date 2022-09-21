package com.android.data.utils

fun String.getDigits(): String = replace("[^0-9]".toRegex(), "")