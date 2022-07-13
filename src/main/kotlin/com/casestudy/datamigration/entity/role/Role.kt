package com.casestudy.datamigration.entity.role

import com.casestudy.datamigration.entity.EntitySuperClass
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "Role")
class Role(accountUserId: Int, val Role: String) : EntitySuperClass(accountUserId){

}