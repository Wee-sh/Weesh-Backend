package wee.sh.domain.tree.presentation.dto.response

import wee.sh.domain.tree.domain.Tree

data class TreeResponse(
    val starCount: Int,
    val giftBoxCount: Int,
    val templateId: Int
) {
    companion object {
        fun from(tree: Tree): TreeResponse {
            return TreeResponse(
                starCount = tree.starCount,
                giftBoxCount = tree.giftBoxCount,
                templateId = tree.user.templateId
            )
        }
    }
}
