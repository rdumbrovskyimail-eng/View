package com.design.template.util

import java.text.SimpleDateFormat
import java.util.*

fun Long.toFormattedDate(pattern: String = "MMM dd, yyyy HH:mm"): String {
    val sdf = SimpleDateFormat(pattern, Locale.getDefault())
    return sdf.format(Date(this))
}

fun String.isValidEmail(): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}
