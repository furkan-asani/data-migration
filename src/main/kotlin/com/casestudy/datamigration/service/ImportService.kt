package com.casestudy.datamigration.service

import com.casestudy.datamigration.csvutil.CSVUtil
import com.casestudy.datamigration.entity.User
import com.casestudy.datamigration.repository.UserRepository
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.IOException

@Service
class ImportService {

    companion object : KLogging()

    @Autowired
    lateinit var userRepository: UserRepository

    fun importDataToDatabase(file: MultipartFile) {

        try {
            val users: List<User> = CSVUtil.csvToUser(file.inputStream)
            userRepository.saveAll(users)
        } catch (e: IOException){
            throw RuntimeException("failed to store csv data: ${e.message}")
        }
    }

    fun getAllUsers(): MutableIterable<User> {
        return userRepository.findAll()
    }

}