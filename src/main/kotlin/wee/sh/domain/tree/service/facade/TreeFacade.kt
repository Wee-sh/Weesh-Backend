package wee.sh.domain.tree.service.facade

import org.springframework.stereotype.Component
import wee.sh.domain.tree.domain.exception.TreeNotFoundException
import wee.sh.domain.tree.domain.repository.TreeRepository
import wee.sh.domain.user.domain.User

@Component
class TreeFacade(
    private val treeRepository: TreeRepository
) {
    fun findByUser(user: User) =
        treeRepository.findByUser(user)
            ?: throw TreeNotFoundException

    fun findByShareToken(shareToken: String) =
        treeRepository.findByShareToken(shareToken)
            ?: throw TreeNotFoundException
}
