package com.example.domain.model

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
    open var name: String = "",

    @OneToOne
    open var grade: Grade? = null,
)
