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
    val names: List<String>,
) {
    companion object {
        @JvmStatic
        fun build(group: Group) = GroupResponse(
            id = group.id,
            title = group.title,
            names = group.users.map { it.fullName }
        )
    }
}
