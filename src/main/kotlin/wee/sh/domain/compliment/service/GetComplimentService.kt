package wee.sh.domain.compliment.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import wee.sh.domain.compliment.domain.repository.ComplimentRepository
import wee.sh.domain.compliment.presentation.dto.response.ComplimentResponse

@Service
class GetComplimentService(
    private val complimentRepository: ComplimentRepository
) {
    /**
     * 내가 보낸 덕담 조회
     */
    @Transactional(readOnly = true)
    fun getSentCompliment(userId: Long): List<ComplimentResponse> {
        val compliments = complimentRepository.findAllByFromUserId(userId)

        return compliments.map { ComplimentResponse.from(it) }
    }

    /**
     * 내가 받은 덕담 조회
     */
    @Transactional(readOnly = true)
    fun getReceivedCompliment(userId: Long): List<ComplimentResponse> {
        val compliments = complimentRepository.findAllByToUserId(userId)

        return compliments.map { ComplimentResponse.from(it) }
    }
}
