package cz.ssps.gradingsystem.domain.responses

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import cz.ssps.gradingsystem.domain.model.Group
import io.quarkus.runtime.annotations.RegisterForReflection

@JsonRootName("group")
@RegisterForReflection
data class GroupResponse(
    @JsonProperty("id")
    val id: Long,

    @JsonProperty
    val title: String,

    @JsonProperty
    val users: List<CompactUserResponse>,

    @JsonProperty
    val memberCount: Int,
) {
    companion object {
        @JvmStatic
        fun build(group: Group) = GroupResponse(
            id = group.id,
            title = group.title,
            users = group.users.map { CompactUserResponse(it.id, it.fullName, it.email) },
            memberCount = group.users.size
        )
    }

    @JsonRootName("user")
    data class CompactUserResponse(
        @JsonProperty
        val id: Long,

        @JsonProperty
        val name: String,

        @JsonProperty
        val email: String,
    )
}
