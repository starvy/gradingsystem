package cz.ssps.gradingsystem.domain.services

import cz.ssps.gradingsystem.domain.UserIsInGroup
import cz.ssps.gradingsystem.domain.UserNotFoundException
import cz.ssps.gradingsystem.domain.repositories.GroupRepository
import cz.ssps.gradingsystem.domain.repositories.UserRepository
import cz.ssps.gradingsystem.domain.requests.GroupUpdateRequest
import cz.ssps.gradingsystem.domain.requests.NewGroupRequest
import cz.ssps.gradingsystem.domain.responses.GroupListResponse
import cz.ssps.gradingsystem.domain.responses.GroupResponse
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class GroupService(
    val groupRepository: GroupRepository,
    private val userRepository: UserRepository,
) {
    /** A new group is created and users are added to it */
    fun createAndAddUsers(groupRequest: NewGroupRequest) = groupRequest.run {
        val group = this.toEntity()
        userIds.forEach { id ->
            userRepository.findById(id).also {
                if (it == null) {
                    throw UserNotFoundException()
                }
                group.users.add(it)
                it.groups.add(group)
            }
        }
        GroupResponse.build(
            group.also {
                groupRepository.persist(it)
            }
        )
    }

    fun updateGroup(groupRequest: GroupUpdateRequest) = groupRequest.run {
        val group = groupRepository.findById(groupId)!!
        userIdsToAdd.forEach { id ->
            userRepository.findById(id)!!.also {
                if (!userRepository.isInGroup(group, it)) { // Don't add user if is already in that group
                    it.groups.add(group)
                    group.users.add(it)
                    groupRepository.persist(group)
                } else {
                    throw UserIsInGroup()
                }
            }
        }
    }

    fun getMyGroups(username: String) = GroupListResponse.build(
        userRepository.findByUsername(username)!!.groups
    )

    fun findById(id: Long) = groupRepository.findById(id)
}
