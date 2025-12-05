package wee.sh.domain.user.service

import org.springframework.stereotype.Service
import wee.sh.domain.user.domain.repository.UserRepository
import wee.sh.domain.user.presentation.dto.request.ChangeNicknameRequest
import wee.sh.domain.user.service.facade.UserFacade

@Service
class ChangeNicknameService(
    private val userFacade: UserFacade,
    private val userRepository: UserRepository
) {
    fun changeNickname(userId: Long, changeNicknameRequest: ChangeNicknameRequest) {
        val user = userFacade.findByUserId(userId)

        user.updateNickname(changeNicknameRequest.nickname)

        userRepository.save(user)
    }
}
