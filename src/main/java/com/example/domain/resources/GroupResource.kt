package com.example.domain.resources

import com.example.domain.requests.GroupUpdateRequest
import com.example.domain.requests.NewGroupRequest
import com.example.domain.services.GroupService
import com.example.infrastructure.security.Role
import javax.annotation.security.RolesAllowed
import javax.transaction.Transactional
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.core.Response
import javax.ws.rs.core.Response.ok

@Path("/group")
class GroupResource(
    private val groupService: GroupService,
) {
    @POST
    @Transactional
    @RolesAllowed(Role.TEACHER, Role.ADMIN, Role.SUPERADMIN)
    fun newGroup(newGroupRequest: NewGroupRequest): Response = ok(groupService.createAndAddUsers(newGroupRequest)).build()

    @Path("/update")
    @POST
    @RolesAllowed(Role.TEACHER, Role.ADMIN, Role.SUPERADMIN)
    fun addUsersToExistingGroup(updateRequest: GroupUpdateRequest): Response = ok(groupService.updateGroup(updateRequest)).build();

    /*@GET
    @Path("/{id}")
    fun */
}