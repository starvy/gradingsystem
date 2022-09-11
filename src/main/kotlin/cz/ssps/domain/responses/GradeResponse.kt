package cz.ssps.domain.responses

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import cz.ssps.domain.model.Grade
import io.quarkus.runtime.annotations.RegisterForReflection

@JsonRootName("grade")
@RegisterForReflection
data class GradeResponse(
    @JsonProperty("name")
    val name: String,

    @JsonProperty("description")
    val description: String,

    @JsonProperty("value")
    val value: Byte,

    @JsonProperty
    val studentId: Long,
){
    companion object {
        @JvmStatic
        fun build(grade: Grade): GradeResponse = GradeResponse(
            name = grade.title,
            description = grade.description,
            value = grade.value,
            studentId = grade.student.id,
        )
    }
}
