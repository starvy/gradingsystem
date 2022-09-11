package com.example.domain.responses

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import io.quarkus.runtime.annotations.RegisterForReflection

@JsonRootName("class")
@RegisterForReflection
data class ClassResponse(
    @JsonProperty
    val students: List<String>,

    @JsonProperty
    val teachers: List<String>,
) {
    companion object {
        @JvmStatic
        fun build(c: com.example.domain.model.Class) = ClassResponse(
            students = c.group.users.map { it.fullName },
            teachers = c.teachers.map { it.fullName }
        )
    }
}
