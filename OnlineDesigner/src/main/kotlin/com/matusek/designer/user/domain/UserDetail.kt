package com.matusek.designer.user.domain

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.sql.Timestamp

class User(
        private val id: String,
        private val email: String,
        private val encryptedPassword: String,
        private val created: Timestamp,
        private val enabled: Boolean,
        private val authorities: MutableList<SimpleGrantedAuthority>
) : UserDetails {

    fun getId(): String {
        return id
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return authorities
    }

    override fun isEnabled(): Boolean {
        return enabled
    }

    override fun getUsername(): String {
        return email
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun getPassword(): String {
        return encryptedPassword
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    fun getCreated(): Timestamp {
        return created
    }

    override fun toString(): String {
        return "User(id='$id', email='$email', authorities=$authorities)"
    }


}