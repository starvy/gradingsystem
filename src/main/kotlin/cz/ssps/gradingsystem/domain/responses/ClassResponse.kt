package cz.ssps.gradingsystem.domain.responses

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import io.quarkus.runtime.annotations.RegisterForReflection

@JsonRootName("class")
@RegisterForReflection
data class ClassResponse(
    val id: Long,

    @JsonProperty
    val title: String,

    @JsonProperty
    val students: List<String>,

    @JsonProperty
    val teachers: List<String>,
) {
    companion object {
        @JvmStatic
        fun build(c: cz.ssps.gradingsystem.domain.model.Class) = ClassResponse(
            id = c.id,
            title = c.title,
            students = c.group.users.map { it.fullName },
            teachers = c.teachers.map { it.fullName }
        )
    }
}
