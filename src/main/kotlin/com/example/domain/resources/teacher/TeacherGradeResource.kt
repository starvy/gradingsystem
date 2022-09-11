package com.example.domain.resources.teacher

import com.example.domain.requests.NewGradeRequest
import com.example.domain.services.GradeService
import com.example.infrastructure.security.RoleType
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
