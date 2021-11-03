package com.example.meet.util

import android.util.Patterns

object Validations {
    fun validatePasswordLength(password: String): Boolean {
        return password.length >= 8
    }

    fun validateEmail(email: String): Boolean {
        return (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches())
    }
}