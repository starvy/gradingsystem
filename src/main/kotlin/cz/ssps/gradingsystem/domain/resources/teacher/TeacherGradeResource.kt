package cz.ssps.gradingsystem.domain.resources.teacher

import cz.ssps.gradingsystem.domain.requests.NewGradeRequest
import cz.ssps.gradingsystem.domain.services.GradeService
import cz.ssps.gradingsystem.infrastructure.security.RoleType
import javax.annotation.security.RolesAllowed
import javax.transaction.Transactional
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.QueryParam
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
    @Path("/grades")
    @POST
    @RolesAllowed(RoleType.TEACHER, RoleType.ADMIN, RoleType.SUPERADMIN)
    @Transactional
    fun addGrades(newGradeRequest: NewGradeRequest, @Context securityContext: SecurityContext): Response = ok(
        gradeService.add(securityContext.userPrincipal.name, newGradeRequest)
    ).status(201).build()

    @Path("/grades")
    @GET
    @RolesAllowed(RoleType.TEACHER, RoleType.ADMIN, RoleType.SUPERADMIN)
    @Transactional
    fun list(
        @QueryParam("studentId") studentId: Long?,
        @QueryParam("classId") classId: Long?,
        @Context securityContext: SecurityContext?
    ): Response = ok(
        gradeService.list(studentId, classId, securityContext?.userPrincipal?.name)
    )
        .build()
}
