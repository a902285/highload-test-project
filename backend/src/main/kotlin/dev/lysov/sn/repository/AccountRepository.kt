package dev.lysov.sn.repository

import dev.lysov.sn.model.Account

interface AccountRepository {
    fun findAll(limit: Int = 20, offset: Int = 0, name: String? = null): List<Account>

    fun findOne(id: Long): Account?
    fun save(account: Account): Account
    fun delete(id: Long)
    fun findByUsername(username: String): Account?
}