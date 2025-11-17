package wee.sh.domain.tree.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import wee.sh.domain.tree.domain.exception.TreeNotFoundException
import wee.sh.domain.tree.domain.repository.TreeRepository
import wee.sh.domain.user.domain.exception.UserNotFoundException
import wee.sh.domain.user.domain.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import wee.sh.domain.tree.domain.Tree
import wee.sh.domain.tree.presentation.dto.response.TreeResponse

@Service
class GetTreeService(
    private val treeRepository: TreeRepository,
    private val userRepository: UserRepository
) {
    @Transactional(readOnly = true)
    fun getTree(userId: Long): TreeResponse { // 추후 엔티티 반환으로 수정할 것임
        val user = userRepository.findByIdOrNull(userId)
            ?: throw UserNotFoundException

        if (user.isDeleted()) {
            throw UserNotFoundException
        }

        val tree = treeRepository.findByUser(user)
            ?: throw TreeNotFoundException

        return TreeResponse.from(tree)
    }
}
