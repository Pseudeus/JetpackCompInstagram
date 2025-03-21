package com.example.jetpackcompinstagram.login.data.network

import com.example.jetpackcompinstagram.login.data.network.response.LoginResponse
import retrofit2.Response
import retrofit2.http.GET


interface LoginClient {
    @GET("v3/89d67e89-0e4b-43fe-9272-4ad349da6719")
    suspend fun doLogin(): Response<LoginResponse>
}