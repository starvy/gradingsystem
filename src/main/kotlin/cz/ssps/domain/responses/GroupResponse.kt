package cz.ssps.domain.responses

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import cz.ssps.domain.model.Group
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
