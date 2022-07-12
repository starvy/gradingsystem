package com.example.domain.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Room( // TODO rename to classroom??
    @Id
    @GeneratedValue
    val id: Long = 0,


)