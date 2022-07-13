package com.casestudy.datamigration.entity

import javax.persistence.*


@Entity
@Table(name="UserAccount")
class User( userAccountId: Int, val Email: String) : EntitySuperClass(userAccountId){
}