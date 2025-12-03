package wee.sh.domain.user.domain.exception

import wee.sh.global.error.exception.ErrorCode
import wee.sh.global.error.exception.WeeShException

object UserNotFoundException : WeeShException(ErrorCode.USER_NOT_FOUND)
