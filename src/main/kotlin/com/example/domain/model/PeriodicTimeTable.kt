package com.example.domain.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany

/** Collection of lessons for every school day, repeats every week
 * TODO add even and odd weeks*/
@Entity
open class PeriodicTimeTable(
    @Id
    @GeneratedValue
    var id: Long = 0,

    var title: String = "",

    var description: String = "",

    @OneToMany
    var periodicTimeSlots: MutableList<PeriodicTimeSlot> = mutableListOf(),
) {
}
