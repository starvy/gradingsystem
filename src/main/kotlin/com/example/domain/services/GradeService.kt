package com.example.domain.services

import com.example.domain.repositories.GradeRepository
import com.example.domain.repositories.UserRepository
import com.example.domain.requests.NewGradeRequest
import com.example.domain.responses.GradeResponse
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class GradeService(
    private val gradeRepository: GradeRepository,
    private val userRepository: UserRepository,
) {
    fun add(newGradeRequest: NewGradeRequest): GradeResponse = newGradeRequest.run {
        println(newGradeRequest)
        GradeResponse.build(
                this.toEntity().also {
                println(studentId)
                it.student = userRepository.findById(studentId)!!
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