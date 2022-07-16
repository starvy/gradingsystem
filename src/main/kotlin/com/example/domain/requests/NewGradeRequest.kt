package com.example.domain.requests

import com.example.domain.model.Grade
import com.example.domain.model.User
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import io.quarkus.runtime.annotations.RegisterForReflection

@JsonRootName("grade")
@RegisterForReflection
data class NewGradeRequest(
    @JsonProperty("title")
    val title: String,
    // TODO add description

    @JsonProperty("studentId")
    val studentId: Long,

    @JsonProperty("value")
    val value: Byte,

) {
    fun toEntity() = Grade(
        title = title,
        student = User(),
        value = value
    )
}