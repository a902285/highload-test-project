package dev.lysov.sn.security

import org.springframework.context.annotation.Bean
import org.springframework.core.io.ResourceLoader
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.Mono


@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
class WebSecurityConfig(
        private val authenticationManager: AuthenticationManager,
        private val securityContextRepository: SecurityContextRepository,
        private val resourceLoader: ResourceLoader
) {

    @Bean
    fun securitygWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http
                .exceptionHandling()
                .authenticationEntryPoint { swe, _ ->
                    Mono.fromRunnable {
                        swe.response.statusCode = HttpStatus.UNAUTHORIZED
                    }
                }.accessDeniedHandler { swe, _ ->
                    Mono.fromRunnable {
                        swe.response.statusCode = HttpStatus.FORBIDDEN;
                    }
                }.and()
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .authenticationManager(authenticationManager)
                .securityContextRepository(securityContextRepository)
                .authorizeExchange()
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .pathMatchers("/",
                        "/favicon.ico",
                        "/**/*.png",
                        "/**/*.gif",
                        "/**/*.svg",
                        "/**/*.jpg",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/**/*.json",
                        "/static/**")
                .permitAll()
                .pathMatchers("/signup",
                        "/home/**",
                        "/view-account/**",
                        "/account")
                .permitAll()
                .pathMatchers("/api/v1/auth/**")
                .permitAll()
                .anyExchange().authenticated()
                .and().build()
    }

    @Bean
    //TODO: review when https://github.com/spring-projects/spring-boot/issues/9785
    fun indexRoutes() = router {
        GET("/") {
            Mono.justOrEmpty(resourceLoader.getResource("classpath:/static/index.html"))
                    .flatMap { ok().bodyValue(it) }
        }
    }

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()
}