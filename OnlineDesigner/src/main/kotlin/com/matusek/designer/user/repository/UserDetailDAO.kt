package com.matusek.designer.user.repository

import java.sql.Timestamp
import javax.persistence.*

@Entity(name = "UserDetailDAO")
@Table(name="user_detail")
data class UserDetailDAO(
    @Id @Column(name = "id") val id: String,
    @Column(name = "email") var email: String,
    @Column(name = "password") var password: String,
    @Column(name = "created") var created: Timestamp,
    @Column(name = "enabled") var enabled: Boolean,
    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER) @JoinColumn(name = "user_id") var roles: MutableCollection<UserRoleDAO>
)