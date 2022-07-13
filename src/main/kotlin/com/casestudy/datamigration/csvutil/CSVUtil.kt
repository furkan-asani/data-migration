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

        private val logger = KLogging()

        private const val type : String = "text/csv"

        fun isCSV(file: MultipartFile): Boolean{
            return type == file.contentType
        }

        fun csvToEntity(inputStream: InputStream, entityHelper: EntityInterface): MutableList<EntitySuperClass> {

            try {

                val fileReader = BufferedReader(InputStreamReader(inputStream, "UTF-8"))

                logger.logger.info { "File has been read" }

                val csvParser = CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())

                logger.logger.info { "File has been parsed" }

                val entities: MutableList<EntitySuperClass> = mutableListOf()

                logger.logger.info { "Mutable list has been created" }

                val csvRecords: Iterable<CSVRecord> = csvParser.records

                for (csvRecord: CSVRecord in csvRecords) {

                    logger.logger.info { "File is being iterated" }

                    val entity: EntitySuperClass = entityHelper.createEntity(csvRecord)

                entities.add(entity)

                }

                return entities

            }catch (e: IOException) {

                throw RuntimeException("failed to parse CSV file: ${e.message}")

            }
        }

    }


}
