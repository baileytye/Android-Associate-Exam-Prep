package com.baileytye.examprep.util

import android.util.Patterns
import java.util.regex.Pattern

fun validateBasicString(string: String): Boolean {
    return string.trim().isNotEmpty()
}

fun validateEmail(string: String): Boolean {
    return validateBasicString(string) && Patterns.EMAIL_ADDRESS.matcher(string).matches()
}

fun validatePositiveDecimal(string: String): Boolean {
    return validateBasicString(string) && (string != ".") && (string.toDouble() > 0)
}

fun validatePhoneNumber(number: String): Boolean {
    val numbers = number.filter { "0123456789".contains(it, true) }
    return validateBasicString(number)
            && (Patterns.PHONE.matcher(number).matches())
            && (numbers.length == 10)
            && !number.contains('.')
}

fun validatePassword(password: String): Boolean {
    val regex =
        Pattern.compile("(?=.*\\W)(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*()\\-_=+{}|?>.<,:;~`’]{8,26}")
    return validateBasicString(password) && regex.matcher(password).matches()
}
