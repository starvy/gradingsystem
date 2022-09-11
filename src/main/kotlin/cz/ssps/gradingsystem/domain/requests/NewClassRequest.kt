package cz.ssps.gradingsystem.domain.requests

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import cz.ssps.gradingsystem.domain.model.Group

@JsonRootName("group")
data class NewClassRequest(
    @JsonProperty
    val title: String,

//    @JsonProperty // TODO description
//    val description: String,

    @JsonProperty
    val groupId: Long,

    @JsonProperty
    val teacherId: Long, // TODO add option to have more than one teacher
) {
    fun toEntity() = cz.ssps.gradingsystem.domain.model.Class( // TODO maybe move userRepository reference somewhere else?
        title = title,
        group = Group(),
        teachers = mutableListOf(),
    )
}
