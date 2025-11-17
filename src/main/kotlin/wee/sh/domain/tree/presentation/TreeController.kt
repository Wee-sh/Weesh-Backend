package wee.sh.domain.tree.presentation

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
    @GetMapping("/{userId}")
    fun getTree(@PathVariable userId: Long): TreeResponse {
        val tree = getTreeService.getTree(userId)
        return TreeResponse.from(tree)
    }
}
