package com.example.domain.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity(name = "t_subjects")
open class Subject(
    @Id
    @GeneratedValue
    var id: Long = 0,

    @Column(nullable = false)
    var name: String = "",
)