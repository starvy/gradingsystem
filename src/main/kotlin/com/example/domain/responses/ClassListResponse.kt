package com.example.domain.responses

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import io.quarkus.runtime.annotations.RegisterForReflection

@JsonRootName("classes")
@RegisterForReflection
data class ClassListResponse (
    @JsonProperty
    val classes: List<ClassResponse>,
) {
    companion object {
        @JvmStatic
        fun build(classes: List<com.example.domain.model.Class>): ClassListResponse = ClassListResponse(
            classes = classes.map { ClassResponse.build(it) }
        )
    }
}

