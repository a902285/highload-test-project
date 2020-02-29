package dev.lysov.sn.service

import dev.lysov.sn.dto.AccountDto
import dev.lysov.sn.dto.AccountSearchDto
import dev.lysov.sn.dto.PageDto
import dev.lysov.sn.dto.SignupRequestDto
import dev.lysov.sn.mapper.toAccount
import dev.lysov.sn.mapper.toAccountDto
import dev.lysov.sn.repository.AccountRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class AccountServiceImpl(
        val accountRepository: AccountRepository
) : AccountService {

    override fun findAll(search: AccountSearchDto) : Mono<PageDto<AccountDto>> {
        val limit = search.limit ?: 20
        val offset = search.offset ?: 0

        return Mono.justOrEmpty(PageDto(
                limit = limit,
                offset = offset,
                data = accountRepository.findAll(
                        limit = limit,
                        offset = offset,
                        name = search.name
                ).map { it.toAccountDto() }
        ))
    }

    override fun findOne(id: Long) = Mono.justOrEmpty(accountRepository.findOne(id)?.toAccountDto())

    override fun create(account: SignupRequestDto) = Mono.justOrEmpty(accountRepository.save(account.toAccount()).toAccountDto())

    override fun update(account: AccountDto) = Mono.justOrEmpty(accountRepository.save(account.toAccount()).toAccountDto())

    override fun delete(id: Long) = accountRepository.delete(id)
}