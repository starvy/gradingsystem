package cz.ssps.gradingsystem.domain.model

import cz.ssps.gradingsystem.infrastructure.ValidationMessages.USERNAME_MUST_MATCH_PATTERN
import cz.ssps.gradingsystem.infrastructure.security.RoleType
import io.quarkus.runtime.annotations.RegisterForReflection
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

/** One User in database containing login data as well as personal information
 * TODO
 * add more personal info to Profile and encrypt, so it can only be accessed by
 * their teachers that have master passwords for the data
 * */
@Entity(name = "t_users")
@RegisterForReflection
open class User(
    @Id
    @GeneratedValue
    var id: Long = 0,


    @Pattern(regexp = "^[a-zA-Z0-9-_]+$", message = USERNAME_MUST_MATCH_PATTERN)
    @Column(unique = true)
    @NotBlank
    open var username: String = "",

    @Email
    @NotBlank
    @Column(unique = true)
    open var email: String = "",

    @NotBlank
    open var password: String = "",

    open var role: String = RoleType.USER,

    open var fullName: String = "",

    @ManyToMany
    @JoinTable(name = "t_users_groups"/*,
        joinColumns = { @JoinColumn(name = "user_id") },
        inverseJoinColumns = { @JoinColumn}*/)
    open var groups: MutableList<Group> = mutableListOf(),

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "student")
    open var grades: MutableList<Grade> = mutableListOf(),

    @ManyToMany(mappedBy = "teachers")
    open var classes: MutableList<Class> = mutableListOf()
)

