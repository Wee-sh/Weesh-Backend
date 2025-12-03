package wee.sh.infra.oauth2.kakao.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class KakaoUserInfo(
    val id: Long,

    @JsonProperty("kakao_account")
    val kakaoAccount: KakaoAccount
) {
    fun toKakaoId(): String = id.toString()

    fun toNickname(): String = kakaoAccount.profile?.nickname ?: "user${id % 10000}"
}

data class KakaoAccount(
    val profile: Profile?
)

data class Profile(
    val nickname: String?
)
