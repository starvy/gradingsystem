package cz.ssps.domain.model

import cz.ssps.infrastructure.security.RoleType
import javax.persistence.*

@Entity(name = "t_roles")
open class Role(
    @Id
    @GeneratedValue
    var id: Long = 0,

    open var role: String = RoleType.USER,

    @ManyToMany
    @JoinTable(name = "t_users_roles")
    open var users: MutableList<User> = mutableListOf(),
)
