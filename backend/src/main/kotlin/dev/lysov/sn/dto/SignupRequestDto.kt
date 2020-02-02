package dev.lysov.sn.dto

data class SignupRequestDto(
        val username: String,
        var password: String,
        var firstName: String? = null,
        var lastName: String? = null,
        var age: Int? = null,
        var gender: Int? = null,
        var city: String? = null,
        var description: String? = null
)