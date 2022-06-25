package com.casestudy.datamigration

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DataMigrationApplication

fun main(args: Array<String>) {
	runApplication<DataMigrationApplication>(*args)
}
