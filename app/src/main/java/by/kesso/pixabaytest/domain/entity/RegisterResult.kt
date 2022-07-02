package by.kesso.pixabaytest.domain.entity

sealed class RegisterResult {
    object Success: RegisterResult()
    data class Error(val error: String): RegisterResult()
}
