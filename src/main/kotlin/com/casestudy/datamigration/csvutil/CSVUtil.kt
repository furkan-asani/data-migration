package com.casestudy.datamigration.csvutil

import com.casestudy.datamigration.entity.User
import com.casestudy.datamigration.entity.user.UserEntityHelper
import mu.KLogging
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.apache.commons.csv.CSVRecord
import org.springframework.web.multipart.MultipartFile
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class CSVUtil {

    companion object {

        val logger = KLogging()

        val type : String = "text/csv"
        val userHeaders: Array<String> = arrayOf<String>("user_id", "mail")
        val roleHeaders: Array<String> = arrayOf<String>("user_id", "role")

        fun isCSV(file: MultipartFile): Boolean{
            return type == file.contentType
        }
        fun csvToUser(inputStream: InputStream): MutableList<User> {

            try {

                val fileReader: BufferedReader = BufferedReader(InputStreamReader(inputStream, "UTF-8"))

                logger.logger.debug { "File has been read" }

                val csvParser: CSVParser = CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())

                logger.logger.debug { "File has been parsed" }

                val users: MutableList<User> = mutableListOf<User>()

                logger.logger.debug { "Mutable list has been created" }

                val csvRecords: Iterable<CSVRecord> = csvParser.records

                for (csvRecord: CSVRecord in csvRecords) {

                    logger.logger.info { "File is being iterated" }

                    val entityHelper = UserEntityHelper

                    val user: User = entityHelper.createEntity(csvRecord)

                    logger.logger.info { "User has been created with the following properties: ${user.UserId} & ${user.Email}" }

                users.add(user)

                logger.logger.info { "User has been added to the list" }

                }

                return users

            }catch (e: IOException) {

                throw RuntimeException("failed to parse CSV file: ${e.message}")

            }
        }

    }


}
