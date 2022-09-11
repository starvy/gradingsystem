package com.example.domain.repositories

import com.example.domain.model.Group
import com.example.domain.model.User
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepositoryBase
import io.quarkus.panache.common.Parameters.with
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class UserRepository : PanacheRepositoryBase<User, Long> {
    fun findByEmail(email: String): User? =
        find("upper(email)", email.toUpperCase().trim()).firstResult()

    fun findByUsername(username: String): User? =
        find("username", username).firstResult()

    fun existsUsername(subjectedUserId: String): Boolean = count(
        query = "id.username = :subjectedUserId",
        params = with("subjectedUserId", subjectedUserId)
    ) > 0

    fun existsEmail(subjectedUserEmail: String): Boolean = count(
        query = "upper(email) = :subjectedUserEmail",
        params = with("subjectedUserEmail", subjectedUserEmail.toUpperCase().trim())
    ) > 0

    fun isInGroup(group: Group, user: User): Boolean = user.groups.contains(group)


    fun isTeacherInClass(user: User, c: com.example.domain.model.Class): Boolean {
        return c.teachers.contains(user)
    }

    fun isUserInClass(user: User, c: com.example.domain.model.Class): Boolean {
        return c.group.users.contains(user)
    }
}
