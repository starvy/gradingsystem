package com.example.domain.services

import com.example.domain.model.Grade
import com.example.domain.repositories.GradeRepository
import com.example.domain.repositories.UserRepository
import com.example.domain.requests.NewGradeRequest
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class GradeService(
    private val gradeRepository: GradeRepository,
    private val userRepository: UserRepository
) {
    fun add(newGradeRequest: NewGradeRequest): Grade = newGradeRequest.run {
        this.toEntity().also {
            it.studentId = userRepository.findById(studentId)!!
            gradeRepository.persist(it)
        }
    }
}