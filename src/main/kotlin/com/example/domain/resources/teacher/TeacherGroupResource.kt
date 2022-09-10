package com.example.domain.resources.teacher

import com.example.domain.requests.GroupUpdateRequest
import com.example.domain.requests.NewGroupRequest
import com.example.domain.responses.ProfileResponse
import com.example.domain.services.GroupService
import com.example.infrastructure.security.Role.ADMIN
import com.example.infrastructure.security.Role.SUPERADMIN
import com.example.infrastructure.security.Role.TEACHER
import java.net.URI
import javax.annotation.security.RolesAllowed
import javax.transaction.Transactional
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.core.Response
import javax.ws.rs.core.Response.*

@Path("/teacher")
class TeacherGroupResource(
    private val groupService: GroupService,
) {
    @Path("/group")
    @POST
    @Transactional
    @RolesAllowed(TEACHER, ADMIN, SUPERADMIN)
    fun newGroup(newGroupRequest: NewGroupRequest): Response {
        groupService.createAndAddUsers(newGroupRequest)
        return created(URI.create("/")).build()
    }

    @Path("/group/update")
    @POST
    @RolesAllowed(TEACHER, ADMIN, SUPERADMIN)
    fun addUsersToExistingGroup(updateRequest: GroupUpdateRequest): Response = ok(groupService.updateGroup(updateRequest)).build()

    @Path("/group/{id}")
    @GET
    @RolesAllowed(TEACHER, ADMIN, SUPERADMIN)
    fun getGroup(@PathParam("id") id: Long): Response {
        println(groupService.groupRepository.findAll().list())
        val group = groupService.findById(id) ?: return status(Status.NOT_FOUND).build()
        val response: List<ProfileResponse> = group.users.map {
            ProfileResponse.build(it)
        }
        return ok(response).build()
    }
}
