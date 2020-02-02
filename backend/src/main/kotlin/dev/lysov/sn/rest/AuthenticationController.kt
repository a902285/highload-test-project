package dev.lysov.sn.rest

import dev.lysov.sn.dto.AccountDto
import dev.lysov.sn.dto.LoginRequestDto
import dev.lysov.sn.dto.LoginResponseDto
import dev.lysov.sn.dto.SignupRequestDto
import org.springframework.http.ResponseEntity

interface AuthenticationController {
    fun signin(loginRequest: LoginRequestDto): ResponseEntity<LoginResponseDto>
    fun signup(account: SignupRequestDto): ResponseEntity<AccountDto>
}