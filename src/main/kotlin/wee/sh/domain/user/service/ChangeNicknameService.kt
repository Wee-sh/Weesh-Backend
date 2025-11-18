package wee.sh.domain.user.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import wee.sh.domain.user.domain.exception.UserNotFoundException
import wee.sh.domain.user.domain.repository.UserRepository
import wee.sh.domain.user.presentation.dto.request.ChangeNicknameRequest

@Service
class ChangeNicknameService(
    private val userRepository: UserRepository
) {
    fun changeNickname(userId: Long, changeNicknameRequest: ChangeNicknameRequest) {
        val user = userRepository.findByIdOrNull(userId)
            ?: throw UserNotFoundException

        user.updateNickname(changeNicknameRequest.nickname)

        userRepository.save(user)
    }
}
