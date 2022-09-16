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
import javax.ws.rs.core.Response
import javax.ws.rs.core.Response.ok

@Path("/teacher/class")
class TeacherClassResource(
    private val classService: ClassService,
) {
    /**
     * Creates new class
     * */
    @Path("")
    @POST
    @Transactional
    @RolesAllowed(TEACHER, ADMIN, SUPERADMIN)
    fun createClass(newClassRequest: NewClassRequest): Response = ok(
        classService.createClass(newClassRequest)
    ).status(201).build()
}
