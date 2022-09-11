package cz.ssps.domain.responses

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import io.quarkus.runtime.annotations.RegisterForReflection

@JsonRootName("grades")
@RegisterForReflection
data class GradeListResponse(
    @JsonProperty
    val grades: List<GradeResponse>
) {
    companion object {
        @JvmStatic
        fun build(grades: List<GradeResponse>) = GradeListResponse(
            grades = grades
        )
    }
}
