package com.casestudy.datamigration.entity

import javax.persistence.*


@Entity
@Table(name="User")
class User (

    @Id
    val UserId: Int?,
    val Email: String
)