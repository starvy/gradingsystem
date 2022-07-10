package com.example.domain.requests

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName

@JsonRootName("student")
data class GetStudentRequest(
    @JsonProperty
    val studentId: Long,
)