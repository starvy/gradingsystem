package cz.ssps.gradingsystem.domain.resources.user

import cz.ssps.gradingsystem.domain.repositories.UserRepository
import cz.ssps.gradingsystem.domain.responses.GradeResponse
import cz.ssps.gradingsystem.domain.services.GradeService
import cz.ssps.gradingsystem.domain.services.GroupService
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.core.Context
import javax.ws.rs.core.SecurityContext

@Path("/grades")
class UserGradeResource(
    private val service: GradeService,
    private val groupService: GroupService,
    private val userRepository: UserRepository,
) {
    @GET
    fun getGrades(
        @Context securityContext: SecurityContext
    ): List<GradeResponse> {
        return service.get(securityContext.userPrincipal.name)
    }
}
