package wee.sh.domain.compliment.presentation.dto.response

import wee.sh.domain.compliment.domain.Compliment
import java.time.LocalDateTime

data class ComplimentResponse(
    val id: Long,
    val fromUserName: String,
    val toUserName: String,
    val content: String,
    val giftTemplate: Int,
    val createdAt: LocalDateTime
) {
    companion object {
        fun from(compliment: Compliment): ComplimentResponse {
            return ComplimentResponse(
                id = compliment.id,
                fromUserName = if (compliment.isAnonymous) "익명" else compliment.fromUser.nickname,
                toUserName = compliment.toUser.nickname,
                content = compliment.content,
                giftTemplate = compliment.giftTemplate,
                createdAt = compliment.createdAt
            )
        }
    }
}
