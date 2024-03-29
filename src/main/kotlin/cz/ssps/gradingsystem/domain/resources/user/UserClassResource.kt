package cz.ssps.gradingsystem.domain.resources.user

import cz.ssps.gradingsystem.domain.responses.ClassListResponse
import cz.ssps.gradingsystem.domain.services.ClassService
import io.quarkus.security.Authenticated
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.core.Context
import javax.ws.rs.core.Response
import javax.ws.rs.core.Response.ok
import javax.ws.rs.core.SecurityContext

@Path("/classes")
class UserClassResource(
    private val classService: ClassService,
) {
    /**
     * Get classes where user participates
     * */
    @Path("")
    @GET
    @Authenticated
    fun getClasses(@Context securityContext: SecurityContext): Response = (
        ok(ClassListResponse.build(classService.getMyClasses(securityContext.userPrincipal.name))).build()
    )

}
