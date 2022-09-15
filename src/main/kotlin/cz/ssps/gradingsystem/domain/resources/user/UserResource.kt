package cz.ssps.gradingsystem.domain.resources.user

import cz.ssps.gradingsystem.domain.requests.UserLoginRequest
import cz.ssps.gradingsystem.domain.requests.UserRegistrationRequest
import cz.ssps.gradingsystem.domain.services.UserService
import cz.ssps.gradingsystem.infrastructure.security.RoleType.SUPERADMIN
import io.quarkus.security.Authenticated
import javax.annotation.security.PermitAll
import javax.annotation.security.RolesAllowed
import javax.transaction.Transactional
import javax.validation.Valid
import javax.validation.constraints.NotNull
import javax.ws.rs.*
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.core.Response.ok
import javax.ws.rs.core.SecurityContext


@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
class UserResource(
    private val service: UserService
) {

    @POST
    @Path("users")
    @Transactional
    @PermitAll
    fun register(
        @Valid @NotNull newUser: UserRegistrationRequest,
    ): Response = ok(
        service.register(newUser)
    ).status(Response.Status.CREATED)
        .build()


    @POST
    @Path("users/login")
    @PermitAll
    fun login(
        @Valid @NotNull userLoginRequest: UserLoginRequest
    ): Response = ok(
        service.login(userLoginRequest)
    ).status(Response.Status.OK)
        .build()


    @GET
    @Path("user")
    @Authenticated
    fun getUser(
        @Context securityContext: SecurityContext
    ): Response = ok(
        service.get(securityContext.userPrincipal.name)
    ).build()

    @GET
    @Path("users")
    @RolesAllowed(SUPERADMIN)
    fun getAllUsers(): Response = ok(
        service.getAll()
    ).build()

}
