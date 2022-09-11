package cz.ssps.domain.services

import cz.ssps.domain.repositories.UserRepository
import cz.ssps.domain.responses.StudentResponse
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class StudentService(
    private val userRepository: UserRepository
) {
    // User getting student data is already authenticated
    fun get(studentId: Long) = StudentResponse.build(userRepository.findById(studentId)!!)
}
