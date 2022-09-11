package cz.ssps.domain.resources.user

import cz.ssps.domain.requests.UserLoginRequest
import cz.ssps.domain.requests.UserRegistrationRequest
import cz.ssps.domain.services.UserService
import cz.ssps.infrastructure.security.RoleType.SUPERADMIN
import io.quarkus.security.Authenticated
import javax.annotation.security.PermitAll
import javax.annotation.security.RolesAllowed
import javax.transaction.Transactional
import javax.validation.Valid
import javax.validation.constraints.NotNull
import javax.ws.rs.*
import javax.ws.rs.core.*
import javax.ws.rs.core.Response.ok


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
    ): Response = service.register(newUser).run {
        ok(this).status(Response.Status.CREATED)
            .location(UriBuilder.fromResource(UserResource::class.java).path("users/$username").build())
            .build()
    }


    @POST
    @Path("users/login")
    @PermitAll
    fun login(
        @Valid @NotNull userLoginRequest: UserLoginRequest
    ): Response = ok(service.login(userLoginRequest)).status(Response.Status.OK).build()


    @GET
    @Path("user")
    @Authenticated
    fun getUser(
        @Context securityContext: SecurityContext
    ): Response = ok(service.get(securityContext.userPrincipal.name)).build()

    @GET
    @Path("users")
    @RolesAllowed(SUPERADMIN)
    fun getAllUsers(): Response = ok(service.getAll()).build()

}
