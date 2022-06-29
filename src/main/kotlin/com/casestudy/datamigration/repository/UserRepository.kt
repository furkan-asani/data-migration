package com.casestudy.datamigration.repository

import com.casestudy.datamigration.entity.User
import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<User, Int> {
}