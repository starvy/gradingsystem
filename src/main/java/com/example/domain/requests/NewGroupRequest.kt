package com.example.domain.requests

import com.example.domain.model.Group
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName

@JsonRootName("group")
data class NewGroupRequest(
    @JsonProperty
    val title: String,

    @JsonProperty
    val description: String,

    @JsonProperty
    val userIds: List<Long>,
) {
    fun toEntity() = Group( // TODO maybe move userRepository reference somewhere else?
        title = title,
        description = description,
        users = mutableListOf(),
    )
}
