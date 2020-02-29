package dev.lysov.sn.security

import dev.lysov.sn.util.JwtTokenProvider
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class AuthenticationManager(
        private val jwtTokenProvider: JwtTokenProvider,
        private val userService: UserService
) : ReactiveAuthenticationManager {

    override fun authenticate(authentication: Authentication): Mono<Authentication> {
        val jwt = authentication.credentials.toString()

        return if (jwtTokenProvider.validateToken(jwt)) {
            val userId = jwtTokenProvider.getUserIdFromToken(jwt)
            userService.loadUserById(userId)
                    .map { UsernamePasswordAuthenticationToken(it, null, it.authorities) }
        } else {
            Mono.empty()
        }
    }
}