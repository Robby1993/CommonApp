package com.robinson.checkinternetconnection.extentions

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.formatToString(pattern: String, locale: Locale = Locale.getDefault()): String {
    val dateFormat = SimpleDateFormat(pattern, locale)
    return dateFormat.format(this)
}

fun String.parseToDate(pattern: String, locale: Locale = Locale.getDefault()): Date {
    val dateFormat = SimpleDateFormat(pattern, locale)
    return dateFormat.parse(this) ?: throw IllegalArgumentException("Invalid date format")
}

fun Date.isToday(): Boolean {
    val today = Date()
    val todayString = today.formatToString("yyyy-MM-dd")
    val dateString = this.formatToString("yyyy-MM-dd")
    return dateString == todayString
}

fun Date.isYesterday(): Boolean {
    val yesterday = Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000)
    val yesterdayString = yesterday.formatToString("yyyy-MM-dd")
    val dateString = this.formatToString("yyyy-MM-dd")
    return dateString == yesterdayString
}

fun Date.isInFuture(): Boolean {
    return this > Date()
}

fun Date.isInPast(): Boolean {
    return this < Date()
}
