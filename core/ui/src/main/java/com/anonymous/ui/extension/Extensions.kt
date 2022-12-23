package com.anonymous.ui.extension

import saman.zamani.persiandate.PersianDate
import saman.zamani.persiandate.PersianDateFormat
import java.text.SimpleDateFormat
import java.util.*


fun Long.toDate(): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return sdf.format(this)
}

fun getDateFromToday(days: Int): String {
    val calendar = Calendar.getInstance()
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    calendar.add(Calendar.DAY_OF_YEAR, days)
    return sdf.format(Date(calendar.timeInMillis))
}

fun Long.toHourMin(): String {
    val hourMinFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    val date = Date(this * 1000)
    return hourMinFormat.format(date)
}

fun String.toPersianDate(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
    val date = inputFormat.parse(this)
    return PersianDateFormat("m/d").format(PersianDate(date))
}
