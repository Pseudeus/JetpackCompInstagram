package com.example.jetpackcompinstagram.login.data.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginService @Inject constructor(
    private val loginClient: LoginClient
) {
    suspend fun doLogin(user: String, password: String): Boolean = withContext(Dispatchers.IO) {
        val response = loginClient.doLogin()
        response.body()?.success == true
    }
}