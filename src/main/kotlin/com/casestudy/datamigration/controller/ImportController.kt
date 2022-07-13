package com.casestudy.datamigration.controller

import com.casestudy.datamigration.service.ImportService
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api")
class ImportController {

    companion object: KLogging()

    @Autowired
    lateinit var importService: ImportService

    @PostMapping("/import")
    fun importDataToDatabase(@RequestParam("file") files : List<MultipartFile>): ResponseEntity<String> {
        var message: String

        try {

                importService.importArrayOfFilesToDatabase(files)

                message = "Uploaded ${files.count()}  file/s successfully: "

                return ResponseEntity.status(HttpStatus.OK).body(message)

            }catch (e: Exception){

                logger.info { e.message }

                message = "Could not upload one of the files: !"

                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message)
            }
    }

}