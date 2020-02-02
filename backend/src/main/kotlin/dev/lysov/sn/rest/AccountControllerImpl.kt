package dev.lysov.sn.rest

import dev.lysov.sn.exception.BadRequestException
import dev.lysov.sn.dto.AccountDto
import dev.lysov.sn.service.AccountService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/account")
class AccountControllerImpl(
        private val accountService: AccountService
) : AccountController {

    @GetMapping
    override fun findAll() = accountService.findAll()

    @GetMapping("/{id}")
    override fun findOne(@PathVariable("id") id: Long) = accountService.findOne(id)

    @PutMapping("/{id}")
    override fun update(@PathVariable("id") id: Long, @RequestBody account: AccountDto): AccountDto {
        if (id != account.id) {
            throw BadRequestException("id = $id is incorrect parameter")
        }
        return accountService.update(account)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    override fun delete(@PathVariable("id") id: Long) = accountService.delete(id)
}