package dev.lysov.sn.service

import dev.lysov.sn.dto.AccountDto
import dev.lysov.sn.dto.AccountSearchDto
import dev.lysov.sn.dto.PageDto
import dev.lysov.sn.dto.SignupRequestDto

interface AccountService {
    fun findAll(search: AccountSearchDto): PageDto<AccountDto>
    fun findOne(id: Long): AccountDto?
    fun create(account: SignupRequestDto): AccountDto
    fun update(account: AccountDto): AccountDto
    fun delete(id: Long)
}