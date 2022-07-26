package com.zw.template.models

data class LoginRequest(
    val customerRelationShipNumber: String,
    val password: String,
    val type: Int = 0,
    val deviceId: String = "device",
    val token: String = "demo",
)

data class LoginResponse(
    val id: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val token: String = "demo"
)
