package cz.ssps.gradingsystem.domain.resources.teacher

import cz.ssps.gradingsystem.domain.requests.NewClassRequest
import cz.ssps.gradingsystem.domain.services.ClassService
import cz.ssps.gradingsystem.infrastructure.security.RoleType.ADMIN
import cz.ssps.gradingsystem.infrastructure.security.RoleType.SUPERADMIN
import cz.ssps.gradingsystem.infrastructure.security.RoleType.TEACHER
import javax.annotation.security.RolesAllowed
import javax.transaction.Transactional
import javax.ws.rs.POST
import javax.ws.rs.Path

@Path("/teacher/class")
class TeacherClassResource(
    private val classService: ClassService,
) {
    @Path("")
    @POST
    @Transactional
    @RolesAllowed(TEACHER, ADMIN, SUPERADMIN)
    fun createClass(newClassRequest: NewClassRequest) {
        classService.createClass(newClassRequest)
    }
}
