package dev.lysov.sn.rest

import dev.lysov.sn.dto.AccountDto
import dev.lysov.sn.dto.AccountSearchDto
import dev.lysov.sn.dto.PageDto
import reactor.core.publisher.Mono

interface AccountController {
    fun findAll(search: AccountSearchDto): Mono<PageDto<AccountDto>>
    fun findOne(id: Long): Mono<AccountDto>?
    fun update(id: Long, account: AccountDto): Mono<AccountDto>
    fun delete(id: Long)
}