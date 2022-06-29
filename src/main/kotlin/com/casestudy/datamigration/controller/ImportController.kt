package com.casestudy.datamigration.controller

import com.casestudy.datamigration.csvutil.CSVUtil
import com.casestudy.datamigration.service.ImportService
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

    @Autowired
    lateinit var fileService: ImportService

    @PostMapping("/import")
    fun importDataToDatabase(@RequestParam("file") file : MultipartFile): ResponseEntity<String> {
        var message: String = ""
        if(CSVUtil.isCSV(file)){
            try {
                fileService.importDataToDatabase(file)
                message = "Uploaded the file successfully: ${file.originalFilename}"
                return ResponseEntity.status(HttpStatus.OK).body(message)
            }catch (e: Exception){
                message = "Could not upload the file: ${file.originalFilename} !"
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message)
            }
        }
        message = "Please upload a csv file"
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message)
    }

}