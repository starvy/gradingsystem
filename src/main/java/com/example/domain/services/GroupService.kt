package com.example.domain.services

import com.example.domain.UserNotFoundException
import com.example.domain.repositories.GroupRepository
import com.example.domain.repositories.UserRepository
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

//    fun addUsersToExisting()
}