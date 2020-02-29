package dev.lysov.sn.rest

import dev.lysov.sn.dto.AccountDto
import dev.lysov.sn.dto.AccountSearchDto
import dev.lysov.sn.dto.PageDto
import dev.lysov.sn.exception.BadRequestException
import dev.lysov.sn.service.AccountService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api/v1/account")
class AccountControllerImpl(
        private val accountService: AccountService
) : AccountController {

    companion object {
        private val log = LoggerFactory.getLogger(AccountControllerImpl::class.java)
    }

    @GetMapping
    override fun findAll(search: AccountSearchDto): Mono<PageDto<AccountDto>> {
        log.info("findAll() - start: search = {}", search)
        return accountService.findAll(search)
                .map {
                    log.info("findAll() - end: result = {}", it.data.size)
                    it
                }
    }

    @GetMapping("/{id}")
    override fun findOne(@PathVariable("id") id: Long) = accountService.findOne(id)

    @PutMapping("/{id}")
    override fun update(@PathVariable("id") id: Long, @RequestBody account: AccountDto): Mono<AccountDto> {
        if (id != account.id) {
            throw BadRequestException("id = $id is incorrect parameter")
        }
        return accountService.update(account)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    override fun delete(@PathVariable("id") id: Long) = accountService.delete(id)
}