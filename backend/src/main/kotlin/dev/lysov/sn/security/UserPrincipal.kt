package dev.lysov.sn.security

import com.fasterxml.jackson.annotation.JsonIgnore
import dev.lysov.sn.model.Account
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserPrincipal(
        val id: Long,
        private val username: String,
        @JsonIgnore
        private val password: String,
        private val authorities: MutableCollection<out GrantedAuthority>
) : UserDetails {

    override fun getAuthorities() = authorities

    override fun isEnabled() = true

    override fun getUsername() = username

    override fun isCredentialsNonExpired() = true

    override fun getPassword() = password

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    companion object {
        fun create(account: Account) = UserPrincipal(
                id = account.id!!,
                username = account.username!!,
                password = account.password!!,
                authorities = mutableListOf(SimpleGrantedAuthority("ROLE_USER"))
        )
    }
}