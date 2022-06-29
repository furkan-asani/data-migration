package com.casestudy.datamigration.repository

import com.casestudy.datamigration.entity.Role
import org.springframework.data.repository.CrudRepository

interface RoleRepository: CrudRepository<Role, Int> {
}