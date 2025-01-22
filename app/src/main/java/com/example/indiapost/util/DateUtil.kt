package com.example.indiapost.util

import android.text.format.DateFormat
import java.text.SimpleDateFormat
import java.util.Date

fun parseDate(date: String) : String? {
    var dateText: String? = null
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")

    try {
        val formatted: Date = dateFormat.parse(date) as Date
        val day = DateFormat.format("dd", formatted) as String
        val monthString = DateFormat.format("MMM", formatted) as String
        dateText = "$day $monthString"
    } catch (_:java.lang.Exception) { }

    return dateText
}