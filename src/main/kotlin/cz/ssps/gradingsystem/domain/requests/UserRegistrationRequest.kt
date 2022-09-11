package cz.ssps.gradingsystem.domain.requests

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import cz.ssps.gradingsystem.domain.model.User
import io.quarkus.runtime.annotations.RegisterForReflection

@JsonRootName("user")
@RegisterForReflection
data class UserRegistrationRequest(
    @JsonProperty("username")
    val username: String,

    @JsonProperty("email")
    val email: String,

    @JsonProperty("password")
    val password: String,
) {
    fun toEntity() = User(
        username = username,
        email = email,
        password = password
    )
}
