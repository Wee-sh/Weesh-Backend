package wee.sh.domain.compliment.domain.exception

import wee.sh.global.error.exception.ErrorCode
import wee.sh.global.error.exception.WeeShException

object SelfComplimentNotAllowedException : WeeShException(ErrorCode.SELF_COMPLIMENT_NOT_ALLOWED)
