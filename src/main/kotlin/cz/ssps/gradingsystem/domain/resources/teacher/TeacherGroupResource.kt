package cz.ssps.gradingsystem.domain.resources.teacher

import cz.ssps.gradingsystem.domain.requests.GroupUpdateRequest
import cz.ssps.gradingsystem.domain.requests.NewGroupRequest
import cz.ssps.gradingsystem.domain.responses.GroupResponse
import cz.ssps.gradingsystem.domain.services.GroupService
import cz.ssps.gradingsystem.infrastructure.security.RoleType.ADMIN
import cz.ssps.gradingsystem.infrastructure.security.RoleType.SUPERADMIN
import cz.ssps.gradingsystem.infrastructure.security.RoleType.TEACHER
import javax.annotation.security.RolesAllowed
import javax.transaction.Transactional
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.PUT
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.core.Response
import javax.ws.rs.core.Response.*

@Path("/teacher")
class TeacherGroupResource(
    private val groupService: GroupService,
) {
    /** Creates new group */
    @Path("/group")
    @POST
    @Transactional
    @RolesAllowed(TEACHER, ADMIN, SUPERADMIN)
    fun newGroup(newGroupRequest: NewGroupRequest): Response = ok(
        groupService.new(newGroupRequest)
    ).status(201).build()

    /** Updates existing group */
    @Path("/group/update")
    @PUT
    @RolesAllowed(TEACHER, ADMIN, SUPERADMIN)
    fun addUsersToGroup(updateRequest: GroupUpdateRequest): Response = ok(groupService.update(updateRequest)).build()

    /** Gets group by id */
    @Path("/group/{id}")
    @GET
    @RolesAllowed(TEACHER, ADMIN, SUPERADMIN)
    fun getGroup(@PathParam("id") id: Long): Response {
        println(groupService.groupRepository.findAll().list())
        val group = groupService.findById(id) ?: return status(Status.NOT_FOUND).build()
        val response = GroupResponse.build(group)
        return ok(response).build()
    }
}
