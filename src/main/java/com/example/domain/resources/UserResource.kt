package com.example.domain.resources

import com.example.domain.requests.UserLoginRequest
import com.example.domain.requests.UserRegistrationRequest
import com.example.domain.services.UserService
import javax.annotation.security.PermitAll
import javax.transaction.Transactional
import javax.validation.Valid
import javax.validation.constraints.NotNull
import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.core.UriBuilder

@Path("users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
class UserResource(
    private val service: UserService
) {
    @POST
    @Transactional
    @PermitAll
    fun register(
        @Valid @NotNull newUser: UserRegistrationRequest,
    ): Response = service.register(newUser).run {
        Response.ok(this).status(Response.Status.CREATED)
            .location(UriBuilder.fromResource(UserResource::class.java).path("users/$username").build())
            .build()
    }

    @POST
    @Path("/login")
    @PermitAll
    fun login(
        @Valid @NotNull userLoginRequest: UserLoginRequest
    ): Response = Response.ok(service.login(userLoginRequest)).status(Response.Status.OK).build()


}