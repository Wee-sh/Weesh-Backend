package wee.sh.domain.tree.presentation.dto.response

import wee.sh.domain.tree.domain.Tree

data class TreeResponse(
    val userId: Long,
    val nickName: String,
    val starCount: Int,
    val giftBoxCount: Int,
    val templateId: Int,
    val shareToken: String
) {
    companion object {
        fun from(tree: Tree, nickName: String): TreeResponse {
            return TreeResponse(
                userId = tree.user.id,
                nickName = nickName,
                starCount = tree.starCount,
                giftBoxCount = tree.giftBoxCount,
                templateId = tree.user.templateId,
                shareToken = tree.shareToken
            )
        }
    }
}
