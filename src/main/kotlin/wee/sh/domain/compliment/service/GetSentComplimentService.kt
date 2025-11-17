package wee.sh.domain.compliment.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import wee.sh.domain.compliment.domain.repository.ComplimentRepository
import wee.sh.domain.compliment.presentation.dto.response.ComplimentResponse

@Service
class GetSentComplimentService(
    private val complimentRepository: ComplimentRepository
) {

    @Transactional(readOnly = true)
    fun getSentCompliment(toUserId: Long): List<ComplimentResponse> {
        val compliments = complimentRepository.findAllByFromUserId(toUserId)

        return compliments.map { ComplimentResponse.from(it) }
    }
}
