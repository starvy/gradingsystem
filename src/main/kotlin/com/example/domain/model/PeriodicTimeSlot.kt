package com.example.domain.model

import java.sql.Time
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToOne

/** One lesson in timetable
 * e.g. Czech language lesson Monday 8:00 - 8:45 */
@Entity
open class PeriodicTimeSlot(
    @Id
    @GeneratedValue
    var id: Long = 0,

    open var startTime: Time = Time.valueOf("00:00:00"),

    open var endTime: Time = Time.valueOf("00:00:00"),

    open var title: String = "",

    open var description: String = "",

    @OneToOne // optional - can be null
    open var aClass: Class? = null,

    @OneToOne // optional - can be null
    open var room: Room? = null,

    @OneToOne
    open var group: Group? = null,

    )
