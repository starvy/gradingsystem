package cz.ssps.gradingsystem.domain.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

/** Representation of physical classroom in school */
@Entity
class Room( // TODO rename to classroom??
    @Id
    @GeneratedValue
    val id: Long = 0,


)
