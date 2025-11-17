package wee.sh.domain.tree.domain.exception

import wee.sh.global.error.exception.ErrorCode
import wee.sh.global.error.exception.WeeShException

object TreeNotFoundException : WeeShException(ErrorCode.TREE_NOT_FOUND)
