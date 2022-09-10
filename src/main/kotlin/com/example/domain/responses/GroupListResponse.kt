package com.example.domain.responses

import com.example.domain.model.Group
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import io.quarkus.runtime.annotations.RegisterForReflection

@JsonRootName("groups")
@RegisterForReflection
data class GroupListResponse (
    @JsonProperty
    val groups: List<GroupResponse>,
) {
    companion object {
        @JvmStatic
        fun build(groups: List<Group>): GroupListResponse = GroupListResponse(
           groups = groups.map { GroupResponse.build(it) }
        )
    }
}
