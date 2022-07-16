package com.example.domain.model

import javax.persistence.*

@Entity(name = "t_groups")
class Group(
    @Id
    @GeneratedValue
    var id: Long = 0,

    @Column(length = 255)
    var title: String = "",

    @Column(length = 255)
    var description: String = "",

    @ManyToMany(mappedBy = "groups")
    var users: MutableList<User> = mutableListOf(),
)