package com.example.domain.model

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.OneToOne

@Entity(name = "t_grades")
open class Grade(
    @Id
    @GeneratedValue
    var id: Long = 0,

    @Column(nullable = false)
    open var title: String = "",

    open var description: String = "",

    open var value: Byte = 0,

    @OneToOne
    open var subject: Subject = Subject(),

    @ManyToOne
    open var studentId: User = User(),
)