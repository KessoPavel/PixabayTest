package by.kesso.pixabaytest.repository.login

import by.kesso.pixabaytest.domain.entity.LoginResult
import by.kesso.pixabaytest.domain.entity.RegisterResult
import by.kesso.pixabaytest.domain.repository.LoginRepository
import by.kesso.pixabaytest.repository.login.entiry.User
import by.kesso.pixabaytest.repository.login.entiry.UserDao
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.internal.and
import java.security.MessageDigest
import java.util.concurrent.TimeUnit

class RoomLoginRepository(
    private val dao: UserDao,
): LoginRepository {

    override fun login(email: String, password: String): Single<LoginResult> {
        return dao.getUser(email)
            .subscribeOn(Schedulers.io())
            .delay(1, TimeUnit.SECONDS)
            .map { users ->
                if (users.any { user -> user.password == hashPassword(password) }) {
                    LoginResult.Success
                } else {
                    LoginResult.Error("Wrong email or password")
                }
            }
    }

    override fun register(email: String, password: String): Single<RegisterResult> {
        val user = User(0, email, hashPassword(password))

        return dao.getUser(email)
            .subscribeOn(Schedulers.io())
            .flatMap { users ->
                if (users.isEmpty()) {
                    dao.insert(user)
                        .delay(1, TimeUnit.SECONDS)
                        .map { id ->
                            if (id != 0L) {
                                RegisterResult.Success
                            } else {
                                RegisterResult.Error("Creating new user error.")
                            }
                        }
                } else {
                    Single.just(RegisterResult.Error("User with this email already exist"))
                }
            }
    }

    private fun hashPassword(password: String): String {
        val md: MessageDigest = MessageDigest.getInstance("SHA-512")
        md.reset()
        md.update(password.toByteArray())
        val mdArray: ByteArray = md.digest()
        val sb = StringBuilder(mdArray.size * 2)
        for (b in mdArray) {
            val v: Int = b and 0xff
            if (v < 16) sb.append('0')
            sb.append(Integer.toHexString(v))
        }
        return sb.toString()
    }
}