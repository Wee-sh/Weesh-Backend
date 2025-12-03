package wee.sh.domain.tree.presentation

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import wee.sh.domain.tree.presentation.dto.response.TreeResponse
import wee.sh.domain.tree.service.GetTreeService

@RestController
@RequestMapping("/tree")
class TreeController(
    private val getTreeService: GetTreeService
) {
    @GetMapping("/me")
    fun getMyTree(@AuthenticationPrincipal userId: Long): TreeResponse {
        return getTreeService.getTree(userId)
    }

    @GetMapping("/{shareToken}")
    fun getSharedTree(
        @PathVariable shareToken: String
    ): TreeResponse {
        return getTreeService.getTreeByShareToken(shareToken)
    }
}
