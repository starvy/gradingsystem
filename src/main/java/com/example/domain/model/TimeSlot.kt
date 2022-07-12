package com.example.domain.model

import java.sql.Time
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToOne

@Entity
open class TimeSlot(
    @Id
    @GeneratedValue
    var id: Long = 0,

    open var startTime: Time = Time.valueOf("00:00:00"),

    open var endTime: Time = Time.valueOf("00:00:00"),

    open var title: String = "",
    
    open var description: String = "",
    
    @OneToOne // optional - can be null
    open var subject: Subject? = null, 

    @OneToOne // optional - can be null
    open var room: Room? = null,

    @OneToOne
    open var group: Group? = null,

)