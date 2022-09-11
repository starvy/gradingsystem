package cz.ssps.domain.resources.user

import cz.ssps.domain.repositories.UserRepository
import cz.ssps.domain.responses.GradeResponse
import cz.ssps.domain.services.GradeService
import cz.ssps.domain.services.GroupService
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.core.Context
import javax.ws.rs.core.SecurityContext

@Path("/grades")
class GradeResource(
    private val service: GradeService,
    private val groupService: GroupService,
    private val userRepository: UserRepository,
) {
    /*@POST
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
    }*/
    @GET
    fun getGrades(
        @Context securityContext: SecurityContext
    ): List<GradeResponse> {
        return service.get(securityContext.userPrincipal.name)
    }
}
