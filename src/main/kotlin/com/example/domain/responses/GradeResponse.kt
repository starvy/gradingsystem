package com.example.domain.responses

import com.example.domain.model.Grade
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
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