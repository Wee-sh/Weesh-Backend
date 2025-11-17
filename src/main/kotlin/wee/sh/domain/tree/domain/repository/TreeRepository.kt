package wee.sh.domain.tree.domain.repository

import org.springframework.data.repository.CrudRepository
import wee.sh.domain.tree.domain.Tree
import wee.sh.domain.user.domain.User

interface TreeRepository : CrudRepository<Tree, Long> {
    fun findByUser(user: User): Tree?
}
