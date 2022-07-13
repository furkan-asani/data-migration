package com.casestudy.datamigration.entity.user

import com.casestudy.datamigration.entity.EntityInterface
import org.apache.commons.csv.CSVRecord
import org.springframework.stereotype.Service

@Service
class UserEntityHelper: EntityInterface {

    companion object {

        private val userHeaders: Array<String> = arrayOf<String>("user_id", "mail")

    }
        override fun createEntity(userEntry: CSVRecord): User {
            return User(userEntry.get(userHeaders[0]).toInt(), userEntry.get(userHeaders[1]))
        }
}