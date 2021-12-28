package com.matusek.designer.user.repository

import org.springframework.data.repository.CrudRepository

interface UserDetailRepository : CrudRepository<UserDetailDAO, Long> {
    fun findById(id: String): UserDetailDAO?
    fun findByEmail(email: String): UserDetailDAO?
}