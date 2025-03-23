package com.example.jetpackcompinstagram.login.ui

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcompinstagram.login.domain.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _isLoginEnabled = MutableLiveData<Boolean>()
    val isLoginEnabled: LiveData<Boolean> = _isLoginEnabled

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun onLoginChanged(email: String, passwd: String) {
        _email.postValue(email)
        _password.postValue(passwd)
        onIsLoginEnabledChanged(enableLogin(email, passwd))
    }

    fun onIsLoginEnabledChanged(status: Boolean) {
        _isLoginEnabled.postValue(status)
    }

    fun enableLogin(email: String, password: String) =
        Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 6

    fun onLoginSelected() = viewModelScope.launch {
        _isLoading.postValue(true)
        val result = loginUseCase(email.value!!, password.value!!)
        if (result) {
            // Navegar a siguiente pantalla
            Log.i("log", "result OK")
        }
        _isLoading.postValue(false)
    }
}