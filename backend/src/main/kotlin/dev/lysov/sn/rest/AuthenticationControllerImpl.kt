package dev.lysov.sn.rest

import dev.lysov.sn.dto.LoginRequestDto
import dev.lysov.sn.dto.LoginResponseDto
import dev.lysov.sn.dto.SignupRequestDto
import dev.lysov.sn.exception.BadRequestException
import dev.lysov.sn.security.UserService
import dev.lysov.sn.service.AccountService
import dev.lysov.sn.util.JwtTokenProvider
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.util.StringUtils
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api/v1/auth")
class AuthenticationControllerImpl(
        val jwtTokenProvider: JwtTokenProvider,
        val passwordEncoder: BCryptPasswordEncoder,
        val accountService: AccountService,
        val userService: UserService
) : AuthenticationController {

    @PostMapping("/signin")
    override fun signin(@RequestBody loginRequest: LoginRequestDto): Mono<ResponseEntity<LoginResponseDto>> {
        return userService.loadUserByUsername(loginRequest.username)
                .map {
                    if (passwordEncoder.matches(loginRequest.password, it.password)) {
                        ResponseEntity.ok(LoginResponseDto(
                                token = jwtTokenProvider.generateToken(it),
                                userId = it.id
                        ))
                    } else {
                        ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                    }
                }.onErrorResume {
                    Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build())
                }
    }

    // todo ServerRequest / ServerResponse
    //   Mono userMono = request.bodyToMono(User.class);
    //   ServerResponse.ok().contentType(APPLICATION_JSON).body(BodyInserters.fromObject(new LoginResponse(tokenProvider.generateToken(user))))
    @PostMapping("/signup")
    override fun signup(@RequestBody account: SignupRequestDto) = Mono.justOrEmpty(account)
            .map {
                if (!StringUtils.hasText(it.password)) {
                    throw BadRequestException("password is empty")
                }

                it.password = passwordEncoder.encode(it.password)
                it
            }.flatMap { accountService.create(account) }
            .map { ResponseEntity.status(HttpStatus.CREATED).body(it) }
}