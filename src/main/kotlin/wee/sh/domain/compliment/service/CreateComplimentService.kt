package wee.sh.domain.compliment.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import wee.sh.domain.compliment.domain.Compliment
import wee.sh.domain.compliment.domain.exception.SelfComplimentNotAllowedException
import wee.sh.domain.compliment.domain.repository.ComplimentRepository
import wee.sh.domain.compliment.presentation.dto.request.CreateComplimentRequest
import wee.sh.domain.compliment.presentation.dto.response.ComplimentResponse
import wee.sh.domain.user.domain.exception.UserNotFoundException
import wee.sh.domain.user.domain.repository.UserRepository

@Service
class CreateComplimentService(
    private val complimentRepository: ComplimentRepository,
    private val userRepository: UserRepository
) {

    @Transactional
    fun create(request: CreateComplimentRequest, fromUserId: Long): ComplimentResponse {
        val fromUser = userRepository.findByIdOrNull(fromUserId)
            ?: throw UserNotFoundException

        val toUser = userRepository.findByIdOrNull(request.toUserId)
            ?: throw UserNotFoundException

        if (fromUser == toUser) {
            throw SelfComplimentNotAllowedException
        }

        val compliment = Compliment.create(
            fromUser = fromUser,
            toUser = toUser,
            content = request.content,
            isAnonymous = request.isAnonymous
        )

        val saved = complimentRepository.save(compliment)

        return ComplimentResponse.from(saved)
    }
}
