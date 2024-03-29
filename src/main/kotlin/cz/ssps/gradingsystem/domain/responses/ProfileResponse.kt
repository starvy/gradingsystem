package cz.ssps.gradingsystem.domain.responses

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import cz.ssps.gradingsystem.domain.model.Group
import cz.ssps.gradingsystem.domain.model.User
import io.quarkus.runtime.annotations.RegisterForReflection

@JsonRootName("user_profile")
@RegisterForReflection
data class ProfileResponse(
    @JsonProperty("id")
    val id: Long,

    @JsonProperty("username")
    val username: String,

    @JsonProperty
    val email: String,

    @JsonProperty
    val role: String,

    @JsonProperty
    val groups: List<Group>,

    @JsonProperty
    val grades: List<GradeResponse>,
) {
    companion object {
        @JvmStatic
        fun build(user: User): ProfileResponse = ProfileResponse(
            id = user.id,
            username = user.username,
            email = user.email,
            role = user.role,
            groups = user.groups.map {
                it.users = mutableListOf()
                it.classes = mutableListOf()
                it
            },
            grades = user.grades.map {
                GradeResponse.build(grade = it)
            }
        )
    }
}
