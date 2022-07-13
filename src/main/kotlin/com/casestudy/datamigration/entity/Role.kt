package com.casestudy.datamigration.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "Role")
class Role(accountUserId: Int, val Role: String) : EntitySuperClass(accountUserId){

}