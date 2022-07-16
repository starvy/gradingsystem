package com.example.domain.requests

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName

@JsonRootName("group")
data class GroupUpdateRequest(
    @JsonProperty
    val groupId: Long,

    @JsonProperty
    val userIdsToAdd: List<Long>,
)