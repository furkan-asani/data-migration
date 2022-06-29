package com.casestudy.datamigration.entity

import org.apache.commons.csv.CSVRecord

interface EntityInterface {

    fun createEntity(csvRecord: CSVRecord): EntitySuperClass
}