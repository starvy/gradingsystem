package com.example.domain.repositories

import com.example.domain.model.Grade
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepositoryBase
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class GradeRepository : PanacheRepositoryBase<Grade, Long> {

}