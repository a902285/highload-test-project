package dev.lysov.sn.dto

data class AccountDto(
        val id: Long,
        val username: String,
        var firstName: String? = null,
        var lastName: String? = null,
        var age: Int? = null,
        var gender: Int? = null,
        var city: String? = null,
        var description: String? = null
)