package cz.ssps.gradingsystem.domain.resources.teacher

import cz.ssps.gradingsystem.domain.InvalidUserException
import cz.ssps.gradingsystem.domain.requests.NewClassRequest
import cz.ssps.gradingsystem.domain.services.ClassService
import cz.ssps.gradingsystem.infrastructure.security.RoleType.ADMIN
import cz.ssps.gradingsystem.infrastructure.security.RoleType.SUPERADMIN
import cz.ssps.gradingsystem.infrastructure.security.RoleType.TEACHER
import java.util.UUID
import javax.annotation.security.RolesAllowed
import javax.transaction.Transactional
import javax.ws.rs.DELETE
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.QueryParam
import javax.ws.rs.core.Context
import javax.ws.rs.core.Response
import javax.ws.rs.core.Response.ok
import javax.ws.rs.core.SecurityContext

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

    @Path("")
    @DELETE
    @Transactional
    @RolesAllowed(TEACHER, ADMIN, SUPERADMIN)
    fun deleteClass(@QueryParam("id") id: Long,
    @Context securityContext: SecurityContext) {
        if (classService.isTeacherInClass(securityContext.userPrincipal.name, id)) {
            ok(classService.delete(id)).build()
        } else throw InvalidUserException()
    }
}
