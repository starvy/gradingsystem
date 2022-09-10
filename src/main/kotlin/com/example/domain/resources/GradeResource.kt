package com.example.domain.resources

import com.example.domain.repositories.UserRepository
import com.example.domain.requests.NewGradeRequest
import com.example.domain.responses.GradeResponse
import com.example.domain.services.GradeService
import com.example.domain.services.GroupService
import com.example.infrastructure.security.RoleType
import javax.annotation.security.RolesAllowed
import javax.transaction.Transactional
import javax.validation.Valid
import javax.validation.constraints.NotNull
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.core.Context
import javax.ws.rs.core.Response
import javax.ws.rs.core.Response.ok
import javax.ws.rs.core.Response.status
import javax.ws.rs.core.SecurityContext

@Path("/grades")
class GradeResource(
    private val service: GradeService,
    private val groupService: GroupService,
    private val userRepository: UserRepository,
) {
    @POST
    @Transactional
    @RolesAllowed(RoleType.TEACHER, RoleType.ADMIN, RoleType.SUPERADMIN)
    fun newGrade(
        @Context securityContext: SecurityContext,
        @Valid @NotNull newGradeRequest: NewGradeRequest
    ): Response {
        // teacher has to be in the same group as student in order to give grades
        // group for every subject
        if (groupService.bothInGroup(
            userRepository.findByUsername(securityContext.userPrincipal.name)!!,
                userRepository.findById(newGradeRequest.studentId)!!
            )
        ) {
            return ok(service.add(newGradeRequest)).status(Response.Status.CREATED).build()
        }
        return status(Response.Status.UNAUTHORIZED).build()
    }
    @GET
    fun getGrades(
        @Context securityContext: SecurityContext
    ): List<GradeResponse> {
        return service.get(securityContext.userPrincipal.name)
    }
}
