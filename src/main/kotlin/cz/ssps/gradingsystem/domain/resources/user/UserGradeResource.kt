package cz.ssps.gradingsystem.domain.resources.user

import cz.ssps.gradingsystem.domain.services.GradeService
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.core.Context
import javax.ws.rs.core.Response
import javax.ws.rs.core.Response.ok
import javax.ws.rs.core.SecurityContext

@Path("/grades")
class UserGradeResource(
    private val service: GradeService,
) {
    /**
     * Returns all grades of the user
     */
    @GET
    fun getGrades(
        @Context securityContext: SecurityContext
    ): Response = ok(
        service.get(securityContext.userPrincipal.name)
    ).build()
}
