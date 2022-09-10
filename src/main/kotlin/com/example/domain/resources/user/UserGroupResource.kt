package com.example.domain.resources.user

import com.example.domain.services.GroupService
import com.example.domain.services.UserService
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
    ): Response = ok(groupService.getMyGroups(securityContext.userPrincipal.name)).build()
}
