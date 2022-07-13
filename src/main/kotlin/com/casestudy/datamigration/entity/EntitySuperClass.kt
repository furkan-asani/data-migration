package com.casestudy.datamigration.entity

import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
open abstract class EntitySuperClass(@Id val UserId: Int) {


}