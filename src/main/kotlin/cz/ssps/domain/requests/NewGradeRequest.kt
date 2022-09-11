package cz.ssps.domain.requests

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import cz.ssps.domain.model.Class
import cz.ssps.domain.model.Grade
import cz.ssps.domain.model.User
import io.quarkus.runtime.annotations.RegisterForReflection

@JsonRootName("grade")
@RegisterForReflection
data class NewGradeRequest(
    @JsonProperty("title")
    val title: String,
    // TODO add description

    @JsonProperty("classId")
    val classId: Long,

    @JsonProperty("studentId")
    val studentId: Long,

    @JsonProperty("value")
    val value: Byte,

) {
    fun toEntity() = Grade(
        title = title,
        c = Class(),
        student = User(),
        value = value
    )
}
