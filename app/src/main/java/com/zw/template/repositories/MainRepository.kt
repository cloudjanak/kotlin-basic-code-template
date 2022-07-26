package com.zw.template.repositories

import com.zw.template.api.RetrofitService
import com.zw.template.models.LoginRequest

class LoginRepository constructor(private val retrofitService: RetrofitService) {
    suspend fun login(loginRequest: LoginRequest) = retrofitService.login(loginRequest)
}