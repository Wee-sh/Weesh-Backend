package wee.sh.domain.user.presentation

import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.GetMapping
import wee.sh.domain.user.presentation.dto.request.ChangeNicknameRequest
import wee.sh.domain.user.presentation.dto.response.UserRankResponse
import wee.sh.domain.user.service.ChangeNicknameService
import wee.sh.domain.user.service.GetRankService

@RestController
@RequestMapping("/user")
class UserController(
    private val changeNicknameService: ChangeNicknameService,
    private val userRankService: GetRankService
) {
    @PatchMapping("/nickname")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun changeNickname(
        @AuthenticationPrincipal userId: Long,
        @RequestBody request: ChangeNicknameRequest
    ) {
        changeNicknameService.changeNickname(userId, request)
    }

    @GetMapping("/rank")
    fun getRank() : List<UserRankResponse> {
        return userRankService.getRank()
    }
}
