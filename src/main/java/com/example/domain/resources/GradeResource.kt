package com.example.domain.resources

import com.example.domain.model.Grade
import com.example.domain.requests.NewGradeRequest
import com.example.domain.responses.GradeResponse
import com.example.domain.services.GradeService
import com.example.infrastructure.security.Role
import javax.annotation.security.RolesAllowed
import javax.transaction.Transactional
import javax.validation.Valid
import javax.validation.constraints.NotNull
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.core.Context
import javax.ws.rs.core.Response
import javax.ws.rs.core.SecurityContext

@Path("/grades")
class GradeResource(
    private val service: GradeService,
) {
    @POST
    @Transactional
    @RolesAllowed(Role.TEACHER, Role.ADMIN, Role.SUPERADMIN)
    fun newGrade(
        @Context securityContext: SecurityContext,
        @Valid @NotNull newGradeRequest: NewGradeRequest
    ): Response = Response.ok(service.add(newGradeRequest)).status(Response.Status.CREATED).build()

    @GET
    fun getGrades(
        @Context securityContext: SecurityContext
    ): List<GradeResponse> {
        return service.get(securityContext.userPrincipal.name)
    }
}
