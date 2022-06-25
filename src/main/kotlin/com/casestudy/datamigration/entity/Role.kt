package com.casestudy.datamigration.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "Role")
class Role(
    @Id
    val UserId: Int,
    val Role: String
)