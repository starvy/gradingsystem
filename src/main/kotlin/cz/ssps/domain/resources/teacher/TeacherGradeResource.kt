package cz.ssps.domain.resources.teacher

import cz.ssps.domain.requests.NewGradeRequest
import cz.ssps.domain.services.GradeService
import cz.ssps.infrastructure.security.RoleType
import javax.annotation.security.RolesAllowed
import javax.transaction.Transactional
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.core.Context
import javax.ws.rs.core.SecurityContext

@Path("/teacher")
class TeacherGradeResource(
    private val gradeService: GradeService,
) {
    @Path("/grade")
    @POST
    @RolesAllowed(RoleType.TEACHER, RoleType.ADMIN, RoleType.SUPERADMIN)
    @Transactional
    fun addGrades(newGradeRequest: NewGradeRequest, @Context securityContext: SecurityContext) {
        gradeService.add(securityContext.userPrincipal.name, newGradeRequest)
    }
}
