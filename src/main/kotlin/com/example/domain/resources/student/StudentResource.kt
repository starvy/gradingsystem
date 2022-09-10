package com.example.domain.resources.student

import com.example.domain.services.StudentService
import com.example.infrastructure.security.RoleType
import javax.annotation.security.RolesAllowed
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.core.Response.ok

@Path("/student")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
class StudentResource (
    private val studentService: StudentService,
) {
    @GET
    @Path("/{id}")
    @RolesAllowed(RoleType.TEACHER, RoleType.ADMIN, RoleType.SUPERADMIN) // TODO roles as seperate table with Many To Many relationship with User
    fun getStudent(@PathParam("id") id: Long): Response = ok(studentService.get(id)).build()
}
