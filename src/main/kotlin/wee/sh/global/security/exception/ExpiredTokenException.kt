package wee.sh.global.security.exception

import wee.sh.global.error.exception.ErrorCode
import wee.sh.global.error.exception.WeeShException

object ExpiredTokenException : WeeShException(ErrorCode.EXPIRED_TOKEN)
