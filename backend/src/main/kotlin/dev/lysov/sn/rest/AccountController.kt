package dev.lysov.sn.rest

import dev.lysov.sn.dto.AccountDto

interface AccountController {
    fun findAll(): List<AccountDto>
    fun findOne(id: Long): AccountDto?
    fun update(id: Long, account: AccountDto): AccountDto
    fun delete(id: Long)
}