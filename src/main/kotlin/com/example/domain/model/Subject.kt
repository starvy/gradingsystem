package com.example.domain.model

import javax.persistence.*

@Entity(name = "t_subjects")
open class Subject(
    @Id
    @GeneratedValue
    var id: Long = 0,

    @Column(nullable = false)
    open var name: String = "",

    @OneToOne
    open var grade: Grade? = null,
)