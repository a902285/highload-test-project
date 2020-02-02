package dev.lysov.sn.model

data class Account(
        var id: Long? = null,
        var username: String? = null,
        var password: String? = null,
        var firstName: String? = null,
        var lastName: String? = null,
        var age: Int? = null,
        var gender: Int? = null,
        var city: String? = null,
        var description: String? = null
)