package dev.lysov.sn.util

import dev.lysov.sn.security.UserPrincipal
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtTokenProvider(
        @Value("\${sn.jwtSecret}")
        private val jwtSecret: String,
        @Value("\${sn.jwtExpirationInMs}")
        private val jwtExpirationInMs: Int
) {

    companion object {
        private val log = LoggerFactory.getLogger(JwtTokenProvider::class.java)
    }

    fun generateToken(authentication: Authentication): String {
        val userPrincipal = authentication.principal as UserPrincipal
        val now = Date()
        val expiryDate = Date(now.time + jwtExpirationInMs)

        return Jwts.builder()
                .setSubject(userPrincipal.id.toString())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact()
    }

    fun validateToken(token: String) = try {
        claims(token)
        true
    } catch (e: Exception) {
        log.error("Invalid token: {}", token, e)
        false
    }

    fun getUserIdFromToken(token: String): Long = claims(token).subject.toLong()

    private fun claims(token: String) = Jwts.parser()
            .setSigningKey(jwtSecret)
            .parseClaimsJws(token)
            .body
}