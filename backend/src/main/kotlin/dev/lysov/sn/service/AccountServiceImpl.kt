package dev.lysov.sn.service

import dev.lysov.sn.dto.AccountDto
import dev.lysov.sn.dto.AccountSearchDto
import dev.lysov.sn.dto.PageDto
import dev.lysov.sn.dto.SignupRequestDto
import dev.lysov.sn.mapper.toAccount
import dev.lysov.sn.mapper.toAccountDto
import dev.lysov.sn.repository.AccountRepository
import org.springframework.stereotype.Service

@Service
class AccountServiceImpl(
        val accountRepository: AccountRepository
) : AccountService {

    override fun findAll(search: AccountSearchDto) : PageDto<AccountDto> {
        val limit = search.limit ?: 20
        val offset = search.offset ?: 0

        return PageDto(
                limit = limit,
                offset = offset,
                data = accountRepository.findAll(
                        limit = limit,
                        offset = offset,
                        name = search.name
                ).map { it.toAccountDto() }
        )
    }

    override fun findOne(id: Long) = accountRepository.findOne(id)?.toAccountDto()

    override fun create(account: SignupRequestDto) = accountRepository.save(account.toAccount()).toAccountDto()

    override fun update(account: AccountDto) = accountRepository.save(account.toAccount()).toAccountDto()

    override fun delete(id: Long) = accountRepository.delete(id)
}