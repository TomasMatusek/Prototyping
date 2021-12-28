package com.matusek.designer.user.repository

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity(name = "UserRoleDAO")
@Table(name="user_roles")
data class UserRoleDAO(
    @Id @Column(name = "user_id") var userId: String,
    @Column(name = "role") var role: String
)