package cz.ssps.gradingsystem.domain.model

import javax.persistence.*

/** Group of people learning one subject - e.g. English Class, Math Class
 *
 *  Class consists of students (represented as group) and their teacher(s)  */
@Entity(name = "t_classes")
open class Class(
    @Id
    @GeneratedValue
    var id: Long = 0,

    @Column(nullable = false)
    open var title: String = "",

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = true)
    open var group: Group = Group(),

    @ManyToMany
    @JoinTable(name = "t_users_classes")
    open var teachers: MutableList<User> = mutableListOf(),
)
