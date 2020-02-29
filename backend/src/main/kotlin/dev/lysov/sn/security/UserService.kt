package dev.lysov.sn.security

import dev.lysov.sn.repository.AccountRepository
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class UserService(
        private val accountRepository: AccountRepository
) {
    fun loadUserByUsername(username: String) = Mono.just(username)
            .flatMap { Mono.justOrEmpty(accountRepository.findByUsername(it)) }
            .map { UserPrincipal.create(it) }
            .switchIfEmpty(Mono.error(UsernameNotFoundException("User not found with uthername = $username")))

    fun loadUserById(id: Long) = Mono.justOrEmpty(accountRepository.findOne(id))
            .map { UserPrincipal.create(it) }
            .switchIfEmpty(Mono.error(UsernameNotFoundException("User not found with id = $id")))
}