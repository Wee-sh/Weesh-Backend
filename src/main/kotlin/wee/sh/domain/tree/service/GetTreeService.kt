package wee.sh.domain.tree.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import wee.sh.domain.tree.presentation.dto.response.TreeResponse
import wee.sh.domain.tree.service.facade.TreeFacade
import wee.sh.domain.user.service.facade.UserFacade

@Service
class GetTreeService(
    private val userFacade: UserFacade,
    private val treeFacade: TreeFacade
) {

    @Transactional(readOnly = true)
    fun getTree(userId: Long): TreeResponse {
        val user = userFacade.findByUserId(userId)
        val tree = treeFacade.findByUser(user)

        return TreeResponse.from(tree, user.nickname)
    }

    @Transactional(readOnly = true)
    fun getTreeByShareToken(shareToken: String): TreeResponse {
        val tree = treeFacade.findByShareToken(shareToken)
        val user = userFacade.findByUserId(tree.user.id)

        return TreeResponse.from(tree, user.nickname)
    }
}
