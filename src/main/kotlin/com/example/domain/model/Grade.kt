package com.example.domain.model

import io.quarkus.runtime.annotations.RegisterForReflection
import javax.persistence.*

/** Grade given by teachers that are in the same class as students */
@Entity(name = "t_grades")
@RegisterForReflection
open class Grade(
    @Id
    @GeneratedValue
    var id: Long = 0,

    @Column(nullable = false)
    open var title: String = "",

    open var description: String = "",

    open var value: Byte = 0,

    @ManyToOne
    open var teacher: User? = null,

    @ManyToOne
    open var c: Class? = null,

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = true)
    open var student: User = User(),
)
