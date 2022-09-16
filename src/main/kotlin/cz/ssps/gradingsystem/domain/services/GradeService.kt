package cz.ssps.gradingsystem.domain.services

import cz.ssps.gradingsystem.domain.model.User
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
        val teacher = userRepository.findByUsername(username)!!
        val student = userRepository.findById(this.studentId)!!
        val c = classRepository.findById(this.classId)!!

        if (!(userRepository.isTeacherInClass(teacher, c) &&
                    userRepository.isUserInClass(student, c))) {
            throw ForbiddenException("User is not in class")
        }

        val grade = this.toEntity()
        GradeResponse.build(
            grade.also {
                it.teacher = User(teacher.id)
                it.student = User(student.id)
                it.c = c
                gradeRepository.persist(it)
            }
        )
    }

    fun get(username: String): List<GradeResponse> {
        return userRepository.findByUsername(username)!!.grades.map {
            GradeResponse.build(it)
        }
    }
}
