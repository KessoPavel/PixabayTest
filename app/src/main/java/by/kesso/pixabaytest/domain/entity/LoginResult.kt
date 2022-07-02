package by.kesso.pixabaytest.domain.entity

sealed class LoginResult {
    object Success: LoginResult()
    data class Error(val error: String): LoginResult()
}
