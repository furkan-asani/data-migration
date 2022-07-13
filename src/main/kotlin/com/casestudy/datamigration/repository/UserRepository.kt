package com.casestudy.datamigration.repository

import com.casestudy.datamigration.entity.EntitySuperClass
import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<EntitySuperClass, Int> {
}