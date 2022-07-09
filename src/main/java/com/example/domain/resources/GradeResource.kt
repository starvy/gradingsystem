package com.example.domain.resources

import com.example.domain.requests.NewGradeRequest
import com.example.domain.services.GradeService
import com.example.infrastructure.security.Role
import javax.annotation.security.RolesAllowed
import javax.validation.Valid
import javax.validation.constraints.NotNull
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.core.Context
import javax.ws.rs.core.SecurityContext

@Path("/grades")
class GradeResource(
    private val service: GradeService,
) {
    @POST
    @RolesAllowed(Role.TEACHER, Role.ADMIN, Role.SUPERADMIN)
    fun newGrade(
        @Context securityContext: SecurityContext,
        @Valid @NotNull newGradeRequest: NewGradeRequest
    ) {
        service.add(newGradeRequest)
    }
}
