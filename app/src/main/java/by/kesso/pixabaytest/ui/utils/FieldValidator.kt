package by.kesso.pixabaytest.ui.utils

import android.content.Context
import by.kesso.pixabaytest.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

object FieldValidator {

    fun validateEmail(
        context: Context,
        email: TextInputEditText,
        emailTextInputLayout: TextInputLayout
    ): Boolean {
        if (email.text.toString().trim().isEmpty()) {
            emailTextInputLayout.error = context.getString(R.string.request_field)
            email.requestFocus()
            return false
        } else if (!TextValidator.isValidEmail(email.text.toString())) {
            emailTextInputLayout.error = context.getString(R.string.invalid_email)
            email.requestFocus()
            return false
        } else {
            emailTextInputLayout.isErrorEnabled = false
        }
        return true
    }

    fun validatePassword(
        context: Context,
        password: TextInputEditText,
        passwordTextInputLayout: TextInputLayout
    ): Boolean {
        if (password.text.toString().trim().isEmpty()) {
            passwordTextInputLayout.error = context.getString(R.string.request_field)
            password.requestFocus()
            return false
        } else if (password.text.toString().length !in 8..12) {
            passwordTextInputLayout.error = context.getString(R.string.password_lenght)
            password.requestFocus()
            return false
        } else {
            passwordTextInputLayout.isErrorEnabled = false
        }
        return true
    }


    fun validateConfirmPassword(
        context: Context,
        confirmPassword: TextInputEditText,
        confirmPasswordTextInputLayout: TextInputLayout,
        password: TextInputEditText,
    ): Boolean {
        when {
            confirmPassword.text.toString().trim().isEmpty() -> {
                confirmPasswordTextInputLayout.error = context.getString(R.string.request_field)
                confirmPassword.requestFocus()
                return false
            }
            confirmPassword.text.toString() != password.text.toString() -> {
                confirmPasswordTextInputLayout.error = context.getString(R.string.password_dont_match)
                confirmPassword.requestFocus()
                return false
            }
            else -> {
                confirmPasswordTextInputLayout.isErrorEnabled = false
            }
        }
        return true
    }

    fun validateAge(
        context: Context,
        age: TextInputEditText,
        ageTextInputLayout: TextInputLayout
    ): Boolean {
        when {
            age.text.toString().trim().isEmpty() -> {
                ageTextInputLayout.error = context.getString(R.string.request_field)
                age.requestFocus()
                return false
            }
            age.text.toString().toIntOrNull() == null -> {
                ageTextInputLayout.error = context.getString(R.string.age_not_number)
                age.requestFocus()
                return false
            }
            age.text.toString().toInt() !in 18..99 -> {
                ageTextInputLayout.error = context.getString(R.string.age_range)
                age.requestFocus()
                return false
            }
            else -> {
                ageTextInputLayout.isErrorEnabled = false
            }
        }
        return true
    }
}