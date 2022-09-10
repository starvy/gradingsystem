package com.example.domain.responses

import com.example.domain.model.Group
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import io.quarkus.runtime.annotations.RegisterForReflection

@JsonRootName("group")
@RegisterForReflection
data class GroupResponse(
    @JsonProperty
    val names: List<String>,
) {
    companion object {
        @JvmStatic
        fun build(group: Group) = GroupResponse(
            names = group.users.map { it.fullName }
        )
    }
}