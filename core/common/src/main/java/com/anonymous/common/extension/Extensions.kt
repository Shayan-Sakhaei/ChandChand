package com.anonymous.common.extension

fun String.getDigits(): String = replace("[^0-9]".toRegex(), "")