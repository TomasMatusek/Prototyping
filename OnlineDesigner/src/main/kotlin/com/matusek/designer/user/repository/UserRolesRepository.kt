package com.matusek.designer.user.repository

import org.springframework.data.repository.CrudRepository

interface UserRolesRepository : CrudRepository<UserRoleDAO, Long>