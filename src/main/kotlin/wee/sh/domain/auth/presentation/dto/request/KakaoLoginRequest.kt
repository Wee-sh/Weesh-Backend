package wee.sh.domain.auth.presentation.dto.request

import jakarta.validation.constraints.NotBlank

data class KakaoLoginRequest(
    @field:NotBlank(message = "code는 필수입니다.")
    val code: String
)
