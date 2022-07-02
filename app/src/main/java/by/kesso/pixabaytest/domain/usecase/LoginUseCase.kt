package by.kesso.pixabaytest.domain.usecase

import by.kesso.pixabaytest.domain.entity.LoginResult
import by.kesso.pixabaytest.domain.repository.LoginRepository
import io.reactivex.rxjava3.core.Single

class LoginUseCase(
    private val repository: LoginRepository,
) {

    fun login(email: String, password: String): Single<LoginResult> {
        return repository.login(email, password)
    }
}