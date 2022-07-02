package by.kesso.pixabaytest.domain.repository

import by.kesso.pixabaytest.domain.entity.LoginResult
import by.kesso.pixabaytest.domain.entity.RegisterResult
import io.reactivex.rxjava3.core.Single

interface LoginRepository {
    fun login(email: String, password: String): Single<LoginResult>
    fun register(email: String, password: String): Single<RegisterResult>
}