package by.kesso.pixabaytest.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.kesso.pixabaytest.domain.entity.LoginResult
import by.kesso.pixabaytest.domain.usecase.RegisterUseCase
import by.kesso.pixabaytest.ui.utils.SingleLiveEvent

class RegisterViewModel(
    private val registerUseCase: RegisterUseCase
): ViewModel() {
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    private val _error = SingleLiveEvent<String?>()
    val error: LiveData<String?> = _error

    private val _loading = SingleLiveEvent<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _success = SingleLiveEvent<Unit>()
    val success: LiveData<Unit> = _success

    fun register() {
        registerUseCase.register(email.value?:"", password.value?:"")
            .doOnSubscribe {
                _loading.postValue(true)
            }
            .doOnSuccess { result ->
                when(result) {
                    is LoginResult.Error -> {
                        _error.postValue(result.error)
                    }
                    is LoginResult.Success -> {
                        _success.postCall()
                    }
                }
                _loading.postValue(false)
            }
            .doOnError {
                _error.postValue("Unknown error")
                _loading.postValue(false)
            }
            .subscribe()
    }
}