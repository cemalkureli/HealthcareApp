package com.example.healthcare.Singleton

import java.util.regex.Pattern

object PasswordUtils {
    fun isValid(password: String): Boolean {
        val PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[\$@\$!%*#?&])[A-Za-z\\d\$@\$!%*#?&]{8,}$"
        val pattern = Pattern.compile(PASSWORD_PATTERN)
        val matcher = pattern.matcher(password)
        return matcher.matches()
    }
}
