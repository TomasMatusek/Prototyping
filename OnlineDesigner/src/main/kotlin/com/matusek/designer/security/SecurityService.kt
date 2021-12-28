package com.matusek.designer.security

import com.matusek.designer.user.service.UserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SecurityService : AuthenticationManager {

    @Autowired lateinit var userService: UserService
    @Autowired lateinit var passwordEncoder: BCryptPasswordEncoder
    val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @Transactional
    override fun authenticate(authentication: Authentication): Authentication {
        val email = authentication.name
        val rawPassword = authentication.credentials.toString()
        val userDetails = userService.loadUserByUsername(email) ?: throw UsernameNotFoundException(String.format("User with name %s not found", email))

        if ( ! userDetails.isEnabled) {
            throw DisabledException(String.format("User % is disabled", userDetails.toString()))
        }

        if ( ! passwordEncoder.matches(rawPassword, userDetails.password)) {
            throw BadCredentialsException(String.format("Provided and actual password does not match for user", userDetails.toString()))
        }

        SecurityContextHolder.getContext().authentication = UsernamePasswordAuthenticationToken(authentication.principal, authentication.credentials, authentication.authorities)

        return SecurityContextHolder.getContext().authentication
    }

    fun encodePassword(rawPassword: String): String {
        return passwordEncoder.encode(rawPassword)
    }
}