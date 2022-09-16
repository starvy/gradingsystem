package cz.ssps.gradingsystem.domain.services

import cz.ssps.gradingsystem.domain.repositories.ClassRepository
import cz.ssps.gradingsystem.domain.repositories.UserRepository
import cz.ssps.gradingsystem.domain.requests.NewClassRequest
import cz.ssps.gradingsystem.domain.responses.ClassResponse
import io.quarkus.security.ForbiddenException
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class ClassService(
    private val userRepository: UserRepository,
    private val groupService: GroupService,
    private val classRepository: ClassRepository,
) {
    fun createClass(classRequest: NewClassRequest): ClassResponse = classRequest.run {
        val teacher = userRepository.findById(teacherId)!!
        if (!(teacher.role == "TEACHER" || teacher.role == "ADMIN" || teacher.role == "SUPERADMIN")) {
            throw ForbiddenException("User is not a teacher")
        }

        val c = this.toEntity()
        ClassResponse.build(
            c.also {
                it.group = groupService.findById(groupId)!!
                it.teachers.add(teacher)

                teacher.classes.add(it)
                it.group.classes.add(it)

                classRepository.persist(it)
            }
        )
    }

    fun getMyClasses(username: String): List<cz.ssps.gradingsystem.domain.model.Class> {
        val user = userRepository.findByUsername(username)!!
        val classes: MutableList<cz.ssps.gradingsystem.domain.model.Class> = mutableListOf()
        // Students
        classes.addAll(user.classes)
        // Teachers
        classes.addAll(user.groups.flatMap { it.classes })
        return classes
    }
}
