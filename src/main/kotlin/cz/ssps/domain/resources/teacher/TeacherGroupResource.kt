package cz.ssps.domain.resources.teacher

import cz.ssps.domain.requests.GroupUpdateRequest
import cz.ssps.domain.requests.NewGroupRequest
import cz.ssps.domain.responses.ProfileResponse
import cz.ssps.domain.services.GroupService
import cz.ssps.infrastructure.security.RoleType.ADMIN
import cz.ssps.infrastructure.security.RoleType.SUPERADMIN
import cz.ssps.infrastructure.security.RoleType.TEACHER
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
