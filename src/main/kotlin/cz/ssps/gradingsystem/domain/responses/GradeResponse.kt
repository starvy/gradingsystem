package cz.ssps.gradingsystem.domain.responses

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import cz.ssps.gradingsystem.domain.model.Grade
import io.quarkus.runtime.annotations.RegisterForReflection

@JsonRootName("grade")
@RegisterForReflection
data class GradeResponse(
    @JsonProperty("id")
    val id: Long,

    @JsonProperty("name")
    val name: String,

    @JsonProperty("description")
    val description: String,

    @JsonProperty("value")
    val value: Byte,
){
    companion object {
        @JvmStatic
        fun build(grade: Grade): GradeResponse = GradeResponse(
            id = grade.id,
            name = grade.title,
            description = grade.description,
            value = grade.value,
        )
    }
}
