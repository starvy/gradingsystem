package com.example.domain.responses

import com.example.domain.model.Grade
import com.example.domain.model.User
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName

@JsonRootName("student")
data class StudentResponse(
    @JsonProperty
    val name: String,

    @JsonProperty
    val email: String,

    @JsonProperty
    val fullName: String,

    @JsonProperty
    val grades: List<Grade>,

) {
    companion object {
        @JvmStatic
        fun build(user: User) = StudentResponse(
            name = user.username,
            email = user.email,
            fullName = user.fullName,
            grades = user.grades,
        )
    }
}