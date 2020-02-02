package dev.lysov.sn.service

import dev.lysov.sn.dto.AccountDto
import dev.lysov.sn.dto.SignupRequestDto

interface AccountService {
    fun findAll(): List<AccountDto>
    fun findOne(id: Long): AccountDto?
    fun create(account: SignupRequestDto): AccountDto
    fun update(account: AccountDto): AccountDto
    fun delete(id: Long)
}