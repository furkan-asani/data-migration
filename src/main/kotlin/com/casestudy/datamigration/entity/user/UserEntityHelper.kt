package com.casestudy.datamigration.entity.user

import com.casestudy.datamigration.entity.EntityInterface
import com.casestudy.datamigration.entity.User
import org.apache.commons.csv.CSVRecord

class UserEntityHelper {

    companion object: EntityInterface {

        private val userHeaders: Array<String> = arrayOf<String>("user_id", "mail")

        override fun createEntity(userEntry: CSVRecord): User {
            return User(userEntry.get(userHeaders[0]).toInt(), userEntry.get(userHeaders[1]))
        }
    }
}