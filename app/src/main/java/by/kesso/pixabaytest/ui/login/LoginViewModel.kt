package by.kesso.pixabaytest.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.kesso.pixabaytest.domain.entity.LoginResult
import by.kesso.pixabaytest.domain.usecase.LoginUseCase
import by.kesso.pixabaytest.ui.utils.SingleLiveEvent

class LoginViewModel(
    private val loginUseCase: LoginUseCase
): ViewModel() {

    private val _loginError = SingleLiveEvent<String?>()
    val loginError: LiveData<String?> = _loginError

    private val _loading = SingleLiveEvent<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _success = SingleLiveEvent<Unit>()
    val success: LiveData<Unit> = _success

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    fun login() {
        _loginError.postValue(null)

        loginUseCase.login(email.value?:"", password.value?:"")
            .doOnSubscribe {
                _loading.postValue(true)
            }
            .doOnSuccess { result ->
                when(result) {
                    is LoginResult.Error -> {
                        _loginError.postValue(result.error)
                    }
                    is LoginResult.Success -> {
                        _success.postCall()
                    }
                }
                _loading.postValue(false)
            }
            .doOnError {
                _loginError.postValue("Unknown error")
                _loading.postValue(false)
            }
            .subscribe()
    }

    fun setEmail(newEmail: String) {
        email.value = newEmail
    }
}