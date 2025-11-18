package wee.sh.domain.user.service

import org.springframework.stereotype.Service
import wee.sh.domain.compliment.domain.repository.ComplimentRepository
import wee.sh.domain.user.presentation.dto.response.UserRankResponse

@Service
class GetRankService(
    private val complimentRepository: ComplimentRepository
) {
    fun getRank(): List<UserRankResponse> {
        return complimentRepository.findUserRank().map { projection ->
            UserRankResponse(
                userId = projection.userId,
                nickname = projection.userNickname,
                sentCount = projection.sentCount
            )
        }
    }
}
