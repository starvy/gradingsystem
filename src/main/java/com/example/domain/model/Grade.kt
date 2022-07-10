package com.example.domain.model

import io.quarkus.runtime.annotations.RegisterForReflection
import org.hibernate.annotations.Cascade
import org.hibernate.annotations.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToOne

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

    /*@OneToOne
    open var subject: Subject = Subject(),*/

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = true)
    open var student: User = User(),
)