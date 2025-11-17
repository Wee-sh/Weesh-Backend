package wee.sh.domain.compliment.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import wee.sh.domain.compliment.domain.repository.ComplimentRepository
import wee.sh.domain.compliment.presentation.dto.response.ComplimentResponse

@Service
class GetReceivedComplimentService(
    private val complimentRepository: ComplimentRepository
) {

    @Transactional(readOnly = true)
    fun getReceivedCompliment(fromUserId: Long): List<ComplimentResponse>{
        val compliments = complimentRepository.findAllByToUserId(fromUserId)

        return compliments.map { ComplimentResponse.from(it) }
    }
}
