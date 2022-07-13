package com.casestudy.datamigration.entity.user

import com.casestudy.datamigration.entity.EntitySuperClass
import javax.persistence.*


@Entity
@Table(name="UserAccount")
class User( userAccountId: Int, val Email: String) : EntitySuperClass(userAccountId){
}