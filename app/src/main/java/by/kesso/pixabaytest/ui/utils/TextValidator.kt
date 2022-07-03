package by.kesso.pixabaytest.ui.utils

import android.util.Patterns

object TextValidator {

    fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}