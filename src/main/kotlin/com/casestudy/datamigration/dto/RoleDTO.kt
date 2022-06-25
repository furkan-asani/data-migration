package com.casestudy.datamigration.dto

import javax.validation.constraints.NotBlank

data class RoleDTO (

    val UserId: Int?,
    @get: NotBlank(message = "Role must not be blank")
    val Role: String

        )