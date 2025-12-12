package wee.sh.domain.auth.presentation.dto.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank

@JsonIgnoreProperties(ignoreUnknown = true)
data class KakaoLoginRequest(
    @field:NotBlank(message = "code는 필수입니다.")
    val code: String,
    @field:NotBlank(message = "redirectUri는 필수입니다.")
    @JsonProperty("redirectUri")
    val redirectUri: String
)
