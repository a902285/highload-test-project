package dev.lysov.sn.rest

import dev.lysov.sn.dto.AccountDto
import dev.lysov.sn.dto.AccountSearchDto
import dev.lysov.sn.dto.PageDto

interface AccountController {
    fun findAll(search: AccountSearchDto): PageDto<AccountDto>
    fun findOne(id: Long): AccountDto?
    fun update(id: Long, account: AccountDto): AccountDto
    fun delete(id: Long)
}