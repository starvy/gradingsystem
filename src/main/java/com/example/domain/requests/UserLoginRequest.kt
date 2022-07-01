package com.example.domain.requests

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import io.quarkus.runtime.annotations.RegisterForReflection

@JsonRootName("user")
@RegisterForReflection
data class UserLoginRequest(
    @JsonProperty("email")
    val email: String,

    @JsonProperty("password")
    val password: String,
)
