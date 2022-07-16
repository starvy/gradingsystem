package com.example.domain.model

import com.example.infrastructure.ValidationMessages.USERNAME_MUST_MATCH_PATTERN
import com.example.infrastructure.security.Role
import io.quarkus.runtime.annotations.RegisterForReflection
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

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

    open var role: String = Role.USER,

    open var fullName: String = "",

    @ManyToMany
    @JoinTable(name = "t_users_groups"/*,
        joinColumns = { @JoinColumn(name = "user_id") },
        inverseJoinColumns = { @JoinColumn}*/)
    open var groups: MutableList<Group> = mutableListOf(),

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "student")
    open var grades: MutableList<Grade> = mutableListOf(),
)
