package dev.lysov.sn.security

import dev.lysov.sn.repository.AccountRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
        private val accountRepository: AccountRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val account = accountRepository.findByUsername(username)
                ?: throw UsernameNotFoundException("User not found with uthername = $username")
        return UserPrincipal.create(account)
    }

    fun loadUserById(id: Long): UserDetails {
        val account = accountRepository.findOne(id)
                ?: throw UsernameNotFoundException("User not found with id = $id")
        return UserPrincipal.create(account)
    }
}