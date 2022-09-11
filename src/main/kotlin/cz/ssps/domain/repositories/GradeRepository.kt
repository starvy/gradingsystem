package cz.ssps.domain.repositories

import cz.ssps.domain.model.Grade
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepositoryBase
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class GradeRepository : PanacheRepositoryBase<Grade, Long> {

}
