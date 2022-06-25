package com.casestudy.datamigration.dto

import javax.validation.constraints.NotBlank

data class UserDTO(
    val UserId: Int,
    @get: NotBlank(message = "Email must not be blank")
    val Email: String
)