package wee.sh.domain.user.presentation.dto.response

import wee.sh.domain.user.domain.projection.UserRankProjection

data class UserRankResponse(
    val userId: Long,
    val nickname: String,
    val sentCount: Long,
    val randomTemplate: Int
) {
    companion object {
        fun from(projection: UserRankProjection): UserRankResponse {
            return UserRankResponse(
                userId = projection.userId,
                nickname = projection.userNickname,
                sentCount = projection.sentCount,
                randomTemplate = (1..5).random()
            )
        }
    }
}
