package com.example.domain.services

import com.example.domain.repositories.ClassRepository
import com.example.domain.repositories.UserRepository
import com.example.domain.requests.NewClassRequest
import io.quarkus.security.ForbiddenException
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class ClassService(
    private val userRepository: UserRepository,
    private val groupService: GroupService,
    private val classRepository: ClassRepository,
) {
    fun createClass(classRequest: NewClassRequest) = classRequest.run {
        val teacher = userRepository.findById(teacherId)!!
        if (!(teacher.role == "TEACHER" || teacher.role == "ADMIN" || teacher.role == "SUPERADMIN")) {
            throw ForbiddenException("User is not a teacher")
        }
        val c = this.toEntity()
        c.group = groupService.findById(groupId)!!

        teacher.classes.add(c)
        c.teachers.add(teacher)
        classRepository.persist(c)
    }
}
