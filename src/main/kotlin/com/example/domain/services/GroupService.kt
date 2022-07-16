package com.example.domain.services

import com.example.domain.UserIsInGroup
import com.example.domain.UserNotFoundException
import com.example.domain.repositories.GroupRepository
import com.example.domain.repositories.UserRepository
import com.example.domain.requests.GroupUpdateRequest
import com.example.domain.requests.NewGroupRequest
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class GroupService(
    private val groupRepository: GroupRepository,
    private val userRepository: UserRepository,
) {
    /** A new group is created and users are added to it */
    fun createAndAddUsers(groupRequest: NewGroupRequest) = groupRequest.run {
        val group = this.toEntity()
        userIds.map { id ->
            try {
                userRepository.findById(id).also {
                    it!!.groups.add(group)
                }
            } catch (e: java.lang.NullPointerException) { throw UserNotFoundException() }
        }
        groupRepository.persist(group)
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
}