package com.casestudy.datamigration.service

import com.casestudy.datamigration.csvutil.CSVUtil
import com.casestudy.datamigration.entity.EntityInterface
import com.casestudy.datamigration.entity.EntitySuperClass
import com.casestudy.datamigration.entity.role.RoleEntityHelper
import com.casestudy.datamigration.entity.user.UserEntityHelper
import com.casestudy.datamigration.repository.RoleRepository
import com.casestudy.datamigration.repository.UserRepository
import mu.KLogging
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.IOException

@Service
class ImportService(roleRepository: RoleRepository, userRepository: UserRepository, userEntityHelper: UserEntityHelper, roleEntityHelper: RoleEntityHelper) {

    lateinit var csvNamesToRepository: Map<String, CrudRepository<EntitySuperClass, Int>>
    lateinit var csvNamesToEntityHelper: Map<String, EntityInterface>
    init {

        csvNamesToRepository = mapOf("users.csv" to userRepository, "roles.csv" to roleRepository)
        csvNamesToEntityHelper = mapOf("users.csv" to userEntityHelper as EntityInterface, "roles.csv" to roleEntityHelper as EntityInterface)

    }
    companion object: KLogging()

    fun importArrayOfFilesToDatabase(files: List<MultipartFile>) {

        files.filter{file -> CSVUtil.isCSV(file)}.forEach{
            file -> importDataToDatabase(file)
        }
    }
    fun importDataToDatabase(file: MultipartFile) {

        logger.info { "About to check whether the file can be processed" }

        try {

            if (fileIsNotSupported(file)){

                throw RuntimeException("This file/table is not yet support by the import service!")

            }else{

                logger.info { "Initializing the entityHelper according to the uploaded file" }

                val entityHelper = csvNamesToEntityHelper[file.originalFilename]

                logger.info { "This is the initialized entityHelper: $entityHelper" }

                val repository: CrudRepository<EntitySuperClass, Int> = csvNamesToRepository[file.originalFilename]!!

                logger.info { "This is the initialized repository: $repository" }

                val entities: List<EntitySuperClass> = CSVUtil.csvToEntities(file.inputStream, entityHelper!!)

                logger.info { "Amount of rows in csv: ${entities.size}" }

                repository.saveAll(entities)

                logger.info { "Entries have been saved to the database" }

            }
        } catch (e: IOException){

            throw RuntimeException("failed to store csv data: ${e.message}")
        }
    }

    fun fileIsNotSupported(file: MultipartFile): Boolean {
        return csvNamesToEntityHelper[file.originalFilename] == null || csvNamesToRepository[file.originalFilename] == null
    }

}