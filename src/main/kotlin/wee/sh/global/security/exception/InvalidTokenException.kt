package wee.sh.global.security.exception

import wee.sh.global.error.exception.ErrorCode
import wee.sh.global.error.exception.WeeShException

object InvalidTokenException : WeeShException(ErrorCode.INVALID_TOKEN)
