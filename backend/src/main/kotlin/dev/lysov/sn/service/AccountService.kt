package dev.lysov.sn.service

import dev.lysov.sn.dto.AccountDto
import dev.lysov.sn.dto.AccountSearchDto
import dev.lysov.sn.dto.PageDto
import dev.lysov.sn.dto.SignupRequestDto
import reactor.core.publisher.Mono

interface AccountService {
    fun findAll(search: AccountSearchDto): Mono<PageDto<AccountDto>>
    fun findOne(id: Long): Mono<AccountDto>?
    fun create(account: SignupRequestDto): Mono<AccountDto>
    fun update(account: AccountDto): Mono<AccountDto>
    fun delete(id: Long)
}