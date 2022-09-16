package cz.ssps.gradingsystem.domain.repositories

import cz.ssps.gradingsystem.domain.model.Grade
import cz.ssps.gradingsystem.infrastructure.utils.QueryBuilder
import cz.ssps.gradingsystem.infrastructure.utils.QueryBuilder.SELECT
import cz.ssps.gradingsystem.infrastructure.utils.QueryBuilder.WHERE
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepositoryBase
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class GradeRepository : PanacheRepositoryBase<Grade, Long> {
    fun findBy(studentId: Long?, classId: Long?): List<Grade> = with(hashMapOf<String, Any>()) {
        find(
            query = QueryBuilder()
                .add(SELECT("grades from t_grades as grades"))
                .addIf(
                    studentId != null,
                    WHERE("grades.student_id = :studentId")
                ) { this["studentId"] = studentId!! }
                .addIf(
                    classId != null,
                    WHERE("grades.class_id = :classId")
                ) { this["classId"] = classId!! }
                .build(),
        )
            .list()
    }

}
