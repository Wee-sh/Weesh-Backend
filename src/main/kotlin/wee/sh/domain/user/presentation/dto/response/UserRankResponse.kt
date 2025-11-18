package wee.sh.domain.user.presentation.dto.response

data class UserRankResponse(
    val userId: Long,
    val nickname: String,
    val sentCount: Long
)
