package com.example.domain.resources

import com.example.domain.services.StudentService
import com.example.infrastructure.security.Role
import javax.annotation.security.RolesAllowed
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.core.Response.ok

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
class StudentResource (
    private val studentService: StudentService,
) {
    @GET
    @Path("student/{id}")
    @RolesAllowed(Role.TEACHER, Role.ADMIN, Role.SUPERADMIN) // TODO roles as seperate table with Many To Many relationship with User
    fun getStudent(@PathParam("id") id: Long): Response = ok(studentService.get(id)).build()
}