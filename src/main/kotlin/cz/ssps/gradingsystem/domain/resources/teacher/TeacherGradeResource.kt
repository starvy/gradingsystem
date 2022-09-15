package cz.ssps.gradingsystem.domain.resources.teacher

import cz.ssps.gradingsystem.domain.requests.NewGradeRequest
import cz.ssps.gradingsystem.domain.services.GradeService
import cz.ssps.gradingsystem.infrastructure.security.RoleType
import javax.annotation.security.RolesAllowed
import javax.transaction.Transactional
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.core.Context
import javax.ws.rs.core.Response
import javax.ws.rs.core.Response.ok
import javax.ws.rs.core.SecurityContext

@Path("/teacher")
class TeacherGradeResource(
    private val gradeService: GradeService,
) {
    /** Adds new grade
     * TODO myb refactor to add multiple grades??
     * */
    @Path("/grade")
    @POST
    @RolesAllowed(RoleType.TEACHER, RoleType.ADMIN, RoleType.SUPERADMIN)
    @Transactional
    fun addGrades(newGradeRequest: NewGradeRequest, @Context securityContext: SecurityContext): Response = ok(
        gradeService.add(securityContext.userPrincipal.name, newGradeRequest)
    ).status(201).build()
}
