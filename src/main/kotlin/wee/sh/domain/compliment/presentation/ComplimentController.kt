package wee.sh.domain.compliment.presentation

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PostMapping
import wee.sh.domain.compliment.presentation.dto.request.CreateComplimentRequest
import wee.sh.domain.compliment.presentation.dto.response.ComplimentResponse
import wee.sh.domain.compliment.service.CreateComplimentService
import wee.sh.domain.compliment.service.GetComplimentService

@RestController
@RequestMapping("/compliment")
class ComplimentController(
    private val createComplimentService: CreateComplimentService,
    private val getComplimentService: GetComplimentService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createCompliment(
        @RequestBody @Valid
        request: CreateComplimentRequest,
        @AuthenticationPrincipal userId: Long
    ): ComplimentResponse {
        return createComplimentService.create(request, userId)
    }

    @GetMapping("/sent")
    fun getSentCompliments(
        @AuthenticationPrincipal userId: Long
    ): List<ComplimentResponse> {
        return getComplimentService.getSentCompliment(userId)
    }

    @GetMapping("/received")
    fun getReceivedCompliments(
        @AuthenticationPrincipal userId: Long
    ): List<ComplimentResponse> {
        return getComplimentService.getReceivedCompliment(userId)
    }
}
