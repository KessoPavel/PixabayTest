package by.kesso.pixabaytest.domain.usecase

import by.kesso.pixabaytest.domain.entity.RegisterResult
import by.kesso.pixabaytest.domain.repository.LoginRepository
import io.reactivex.rxjava3.core.Single


class RegisterUseCase(
    private val repository: LoginRepository,
) {

    fun register(email: String, password: String, age: Int): Single<RegisterResult> {
        return repository.register(email, password, age)
    }
}