package dev.lysov.sn.dto

data class LoginResponseDto(
        val token: String,
        val tokenType: String = "Bearer",
        val userId: Long
)