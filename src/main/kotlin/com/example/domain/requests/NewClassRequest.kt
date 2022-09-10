package com.example.domain.requests

import com.example.domain.model.Group
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName

@JsonRootName("group")
data class NewClassRequest(
    @JsonProperty
    val title: String,

//    @JsonProperty // TODO description
//    val description: String,

    @JsonProperty
    val groupId: Long,

    @JsonProperty
    val teacherId: Long, // TODO add option to have more than one teacher
) {
    fun toEntity() = com.example.domain.model.Class( // TODO maybe move userRepository reference somewhere else?
        title = title,
        group = Group(),
        teachers = mutableListOf(),
    )
}
