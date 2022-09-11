package com.example.domain.services

import com.example.domain.repositories.ClassRepository
import com.example.domain.repositories.GradeRepository
import com.example.domain.repositories.UserRepository
import com.example.domain.requests.NewGradeRequest
import com.example.domain.responses.GradeResponse
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

        grade.teacher = teacher
        grade.student = userRepository.findById(studentId)!!

        if (userRepository.isTeacherInClass(teacher, grade.c!!) &&
                userRepository.isUserInClass(grade.student, grade.c!!)
        ) {
            GradeResponse.build(
                grade.also {
                    gradeRepository.persist(it)
                }
            )
        } else {
            throw ForbiddenException("Teacher is not in class")
        }
    }

    fun get(username: String): List<GradeResponse> {
        return userRepository.findByUsername(username)!!.grades.map {
            GradeResponse.build(it)
        }
    }
}
