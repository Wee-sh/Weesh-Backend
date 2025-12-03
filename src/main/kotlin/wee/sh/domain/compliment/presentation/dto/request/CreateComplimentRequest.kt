package wee.sh.domain.compliment.presentation.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class CreateComplimentRequest(
    @field:NotNull(message = "받는 사람 ID는 필수입니다.")
    val toUserId: Long,

    @field:NotBlank(message = "내용은 비어 있을 수 없습니다.")
    @field:Size(max = 200, message = "내용은 최대 200자까지 가능합니다.")
    val content: String,

    @field:NotNull(message = "익명 여부는 필수입니다.")
    val isAnonymous: Boolean
)
