package com.casestudy.datamigration.controller


import com.casestudy.datamigration.service.ImportService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api")
class UserController {

    @Autowired
    lateinit var userService: ImportService

    @GetMapping("/list")
    fun returnAllUserAccounts(){
        userService.getAllUsers()
    }

}