package wee.sh.domain.user.service.facade

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import wee.sh.domain.user.domain.exception.UserNotFoundException
import wee.sh.domain.user.domain.repository.UserRepository
import wee.sh.global.security.exception.InvalidTokenException

@Component
class UserFacade(
    private val userRepository: UserRepository
) {

    fun findByUserId(userId: Long) =
        userRepository.findByIdOrNull(userId)
            ?.takeIf { !it.isDeleted() }
            ?: throw UserNotFoundException

    fun findUserByTokenUserId(userId: Long) =
        userRepository.findByIdOrNull(userId)
            ?.takeIf { !it.isDeleted() }
            ?: throw InvalidTokenException
}
