package cz.ssps.gradingsystem.domain.resources.user

import cz.ssps.gradingsystem.domain.services.GroupService
import io.quarkus.security.Authenticated
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.core.Context
import javax.ws.rs.core.Response
import javax.ws.rs.core.Response.ok
import javax.ws.rs.core.SecurityContext

@Path("/")
class UserGroupResource(
    private val groupService: GroupService,
) {
    @Path("groups")
    @GET
    @Authenticated
    fun getGroups(
        @Context securityContext: SecurityContext,
    ): Response = ok(groupService.getAll(securityContext.userPrincipal.name)).build()
}
