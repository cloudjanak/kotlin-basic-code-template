package com.zw.template.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zw.template.models.LoginRequest
import com.zw.template.models.LoginResponse
import com.zw.template.repositories.LoginRepository
import kotlinx.coroutines.*

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    val liveDataObserver = MutableLiveData<LoginState>()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    fun login(loginRequest: LoginRequest) {
        liveDataObserver.value = LoginState.Loading(true)
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val res = loginRepository.login(loginRequest)
            withContext(Dispatchers.Main) {
                if (res.isSuccessful) {
                    liveDataObserver.value = LoginState.Success(res.body()!!)
                } else {
                    onError("Error : ${res.message()} ")
                }
            }
        }
    }

    private fun onError(message: String) {
        liveDataObserver.postValue(LoginState.Error(message))
    }
}

sealed class LoginState {
    data class Loading(val isLoading: Boolean) : LoginState()
    data class Success(val loginResponse: LoginResponse) : LoginState()
    data class Error(val error: String) : LoginState()
}