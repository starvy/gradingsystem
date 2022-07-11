package com.example.domain.services

import com.example.domain.repositories.UserRepository
import com.example.domain.requests.GetStudentRequest
import com.example.domain.responses.StudentResponse
import com.example.domain.responses.UserResponse
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class StudentService(
    private val userRepository: UserRepository
) {
    // User getting student data is already authenticated
    fun get(studentId: Long) = StudentResponse.build(userRepository.findById(studentId)!!)
}