package com.example.domain.model

import javax.persistence.*

/** One group of people in school e.g. 1.A, 2.B, 1.C 1.K😎 */
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
