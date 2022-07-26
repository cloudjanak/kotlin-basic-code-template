package com.zw.template.api

import com.zw.template.models.LoginRequest
import com.zw.template.models.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RetrofitService {

    @Headers("Content-Type: application/json")
    @POST("/api/Customer/login")
    suspend fun login(
        @Body login: LoginRequest,
    ): Response<LoginResponse>

}