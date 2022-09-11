package cz.ssps.domain.responses

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import cz.ssps.domain.model.User
import io.quarkus.runtime.annotations.RegisterForReflection

@JsonRootName("user")
@RegisterForReflection
data class UserResponse(
    @JsonProperty
    val id: Long,

    @JsonProperty("username")
    val username: String,

    @JsonProperty("email")
    val email: String,

    @JsonProperty("token")
    val token: String,
) {
    companion object {
        @JvmStatic
        fun build(user: User, token: String): UserResponse = UserResponse(
            id = user.id,
            username = user.username,
            email = user.email,
            token = token,
        )
    }
}
