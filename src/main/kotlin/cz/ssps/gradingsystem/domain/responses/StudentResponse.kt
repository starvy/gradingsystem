package cz.ssps.gradingsystem.domain.responses

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import cz.ssps.gradingsystem.domain.model.User

@JsonRootName("student")
data class StudentResponse(
    @JsonProperty
    val name: String,

    @JsonProperty
    val email: String,

    @JsonProperty
    val fullName: String,

    @JsonProperty // TODO myb list grades
    val grades: List<GradeResponse>,

) {
    companion object {
        @JvmStatic
        fun build(user: User) = StudentResponse(
            name = user.username,
            email = user.email,
            fullName = user.fullName,
            grades = user.grades.map {
                GradeResponse.build(it)
            }
        )
    }
}
