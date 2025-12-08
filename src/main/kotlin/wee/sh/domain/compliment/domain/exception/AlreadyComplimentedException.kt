package wee.sh.domain.compliment.domain.exception

import wee.sh.global.error.exception.ErrorCode
import wee.sh.global.error.exception.WeeShException

object AlreadyComplimentedException : WeeShException(ErrorCode.ALREADY_COMPLIMENTED)
