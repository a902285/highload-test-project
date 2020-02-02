package dev.lysov.sn.rest

import dev.lysov.sn.dto.AccountDto
import dev.lysov.sn.dto.LoginRequestDto
import dev.lysov.sn.dto.LoginResponseDto
import dev.lysov.sn.dto.SignupRequestDto
import dev.lysov.sn.service.AccountService
import dev.lysov.sn.util.JwtTokenProvider
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("/api/v1/auth")
class AuthenticationControllerImpl(
        val authenticationManager: AuthenticationManager,
        val jwtTokenProvider: JwtTokenProvider,
        val passwordEncoder: BCryptPasswordEncoder,
        val accountService: AccountService
) : AuthenticationController {

    @PostMapping("/signin")
    override fun signin(@RequestBody loginRequest: LoginRequestDto): ResponseEntity<LoginResponseDto> {
        val authentication = authenticationManager.authenticate(UsernamePasswordAuthenticationToken(
                loginRequest.username, loginRequest.password
        ))
        SecurityContextHolder.getContext().authentication = authentication
        val jwt = jwtTokenProvider.generateToken(authentication)
        return ResponseEntity.ok(LoginResponseDto(jwt))
    }

    @PostMapping("/signup")
    override fun signup(@RequestBody account: SignupRequestDto): ResponseEntity<AccountDto> {

        account.password = passwordEncoder.encode(account.password)
        val result = accountService.create(account)

        val location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/v1/account/{id}")
                .buildAndExpand(result.id).toUri()

        return ResponseEntity.created(location).body(result)
    }
}