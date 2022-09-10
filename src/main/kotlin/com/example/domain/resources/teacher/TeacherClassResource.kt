package com.example.domain.resources.teacher

import com.example.domain.UserNotFoundException
import com.example.domain.requests.NewClassRequest
import com.example.domain.requests.NewGroupRequest
import com.example.domain.services.ClassService
import com.example.infrastructure.security.RoleType.ADMIN
import com.example.infrastructure.security.RoleType.SUPERADMIN
import com.example.infrastructure.security.RoleType.TEACHER
import javax.annotation.security.RolesAllowed
import javax.transaction.Transactional
import javax.ws.rs.POST
import javax.ws.rs.Path

@Path("/teacher/class")
class TeacherClassResource(
    private val classService: ClassService,
) {
    @Path("")
    @POST
    @Transactional
    @RolesAllowed(TEACHER, ADMIN, SUPERADMIN)
    fun createClass(newClassRequest: NewClassRequest) {
        classService.createClass(newClassRequest)
    }
}
