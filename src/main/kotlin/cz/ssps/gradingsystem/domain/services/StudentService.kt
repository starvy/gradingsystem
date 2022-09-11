package cz.ssps.gradingsystem.domain.services

import cz.ssps.gradingsystem.domain.repositories.UserRepository
import cz.ssps.gradingsystem.domain.responses.StudentResponse
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class StudentService(
    private val userRepository: UserRepository
) {
    // User getting student data is already authenticated
    fun get(studentId: Long) = StudentResponse.build(userRepository.findById(studentId)!!)
}
