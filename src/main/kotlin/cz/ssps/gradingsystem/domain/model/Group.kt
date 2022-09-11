package cz.ssps.gradingsystem.domain.model

import javax.persistence.*

/** One group of people in school e.g. 1.A, 2.B, 1.C 1.K😎 */
@Entity(name = "t_groups")
open class Group(
    @Id
    @GeneratedValue
    var id: Long = 0,

    @Column(length = 255)
    open var title: String = "",

    @Column(length = 255)
    open var description: String = "",

    @ManyToMany(mappedBy = "groups")
    open var users: MutableList<User> = mutableListOf(),

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "group")
    open var classes: MutableList<Class> = mutableListOf(),

)
