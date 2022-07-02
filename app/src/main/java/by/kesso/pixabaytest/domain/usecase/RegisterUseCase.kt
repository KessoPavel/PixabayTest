package by.kesso.pixabaytest.domain.usecase

import by.kesso.pixabaytest.domain.entity.LoginResult
import io.reactivex.rxjava3.core.Single
import java.util.concurrent.TimeUnit


class RegisterUseCase {

    fun register(email: String, password: String): Single<LoginResult> {
        return Single.just<LoginResult>(LoginResult.Success)
            .delay(5, TimeUnit.SECONDS)
    }
}