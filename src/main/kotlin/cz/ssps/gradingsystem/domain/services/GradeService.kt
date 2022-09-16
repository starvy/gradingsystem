package cz.ssps.gradingsystem.domain.services

import cz.ssps.gradingsystem.domain.repositories.ClassRepository
import cz.ssps.gradingsystem.domain.repositories.GradeRepository
import cz.ssps.gradingsystem.domain.repositories.UserRepository
import cz.ssps.gradingsystem.domain.requests.NewGradeRequest
import cz.ssps.gradingsystem.domain.responses.GradeResponse
import io.quarkus.security.ForbiddenException
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class GradeService(
    private val gradeRepository: GradeRepository,
    private val userRepository: UserRepository,
    private val classRepository: ClassRepository,
) {
    fun add(username: String, newGradeRequest: NewGradeRequest): GradeResponse = newGradeRequest.run {
        val grade = this.toEntity()
        grade.c = classRepository.findById(classId)!!
        val teacher = userRepository.findByUsername(username)!!

        println("cs")

        if (!(userRepository.isTeacherInClass(teacher, grade.c!!) &&
            userRepository.isUserInClass(grade.student, grade.c!!))) {
                throw ForbiddenException("User is not in class")
        }

        GradeResponse.build(
            grade.also {
                it.teacher = teacher
                it.student = userRepository.findById(studentId)!!
                gradeRepository.persist(it)
            }
        )

    }

    fun list(
        studentId: Long? = null,
        classId: Long? = null,
        username: String? = null
    ) {
        val grades = gradeRepository.findBy(studentId, classId)
        grades.map { GradeResponse.build(it) }
    }

    fun get(username: String): List<GradeResponse> {
        return userRepository.findByUsername(username)!!.grades.map {
            GradeResponse.build(it)
        }
    }
}
