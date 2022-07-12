package com.example.domain.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
open class TimeTable(
    @Id
    @GeneratedValue
    var id: Long = 0,

    var title: String = "",

    var description: String = "",

    @OneToMany
    var timeSlots: MutableList<TimeSlot> = mutableListOf(),
) {
}