package com.casestudy.datamigration.csvutil

import com.casestudy.datamigration.entity.EntityInterface
import com.casestudy.datamigration.entity.EntitySuperClass
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


        fun isCSV(file: MultipartFile): Boolean{
            return type == file.contentType
        }
        fun csvToUser(inputStream: InputStream, entityHelper: EntityInterface): MutableList<EntitySuperClass> {

            try {

                val fileReader: BufferedReader = BufferedReader(InputStreamReader(inputStream, "UTF-8"))

                logger.logger.info { "File has been read" }

                val csvParser: CSVParser = CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())

                logger.logger.info { "File has been parsed" }

                val entities: MutableList<EntitySuperClass> = mutableListOf<EntitySuperClass>()

                logger.logger.info { "Mutable list has been created" }

                val csvRecords: Iterable<CSVRecord> = csvParser.records

                for (csvRecord: CSVRecord in csvRecords) {

                    logger.logger.info { "File is being iterated" }

                    val user: EntitySuperClass = entityHelper.createEntity(csvRecord)

                entities.add(user)

                }

                return entities

            }catch (e: IOException) {

                throw RuntimeException("failed to parse CSV file: ${e.message}")

            }
        }

    }


}
