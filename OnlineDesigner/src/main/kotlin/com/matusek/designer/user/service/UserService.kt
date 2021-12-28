package com.matusek.designer.user.service

import com.matusek.designer.security.SecurityService
import com.matusek.designer.user.domain.User
import com.matusek.designer.user.repository.UserDetailDAO
import com.matusek.designer.user.repository.UserDetailRepository
import com.matusek.designer.user.repository.UserRoleDAO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.time.LocalDateTime
import java.util.*

@Service
class UserService : UserDetailsService {

    @Autowired lateinit var securityService: SecurityService
    @Autowired lateinit var userDetailRepository: UserDetailRepository

    override fun loadUserByUsername(email: String?): UserDetails? {
        if (email == null) return null
        val userDetailDTO = userDetailRepository.findByEmail(email) ?: return null
        return toUser(userDetailDTO)
    }

    fun register(email: String, rawPassword: String, rawPasswordRepeated: String): User {
        val userId = UUID.randomUUID().toString().replace("-", "")
        val encryptedPassword = securityService.encodePassword(rawPassword)
        val now = Timestamp.valueOf(LocalDateTime.now())
        val newUserDTO = UserDetailDAO(userId, email, encryptedPassword, now, true, mutableListOf(UserRoleDAO(userId, "ROLE_USER")))
        userDetailRepository.save(newUserDTO)
        return toUser(newUserDTO)
    }

    private fun toUser(userDetailDTO: UserDetailDAO): User {
        val grantedAuthorities = userDetailDTO.roles
                .flatMap { userDetailDTO.roles }
                .map { roleDAO -> SimpleGrantedAuthority(roleDAO.role) }
                .toCollection(mutableListOf())
        return User(
            userDetailDTO.id,
            userDetailDTO.email,
            userDetailDTO.password,
            userDetailDTO.created,
            userDetailDTO.enabled,
            grantedAuthorities
        )
    }
}