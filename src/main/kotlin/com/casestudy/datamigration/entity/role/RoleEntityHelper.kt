package com.casestudy.datamigration.entity.role

import com.casestudy.datamigration.entity.EntityInterface
import com.casestudy.datamigration.entity.Role
import com.casestudy.datamigration.entity.User
import org.apache.commons.csv.CSVRecord
import org.springframework.stereotype.Service

@Service
class RoleEntityHelper: EntityInterface {

    companion object {

        val roleHeaders: Array<String> = arrayOf<String>("user_id", "role")

    }
        override fun createEntity(roleEntry: CSVRecord): Role {
            return Role(roleEntry.get(roleHeaders[0]).toInt(), roleEntry.get(roleHeaders[1]))
        }
}