package cz.ssps.gradingsystem.domain.services

import cz.ssps.gradingsystem.domain.repositories.ClassRepository
import cz.ssps.gradingsystem.domain.repositories.UserRepository
import cz.ssps.gradingsystem.domain.requests.NewClassRequest
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
        c.teachers.add(teacher)

        teacher.classes.add(c)
        c.group.classes.add(c)

        classRepository.persist(c)
    }

    fun getMyClasses(username: String): List<cz.ssps.gradingsystem.domain.model.Class> {
        val user = userRepository.findByUsername(username)!!
        val classes: MutableList<cz.ssps.gradingsystem.domain.model.Class> = mutableListOf()
        classes.addAll(user.classes)
        classes.addAll(user.groups.flatMap { it.classes })
        return classes
    }
}
