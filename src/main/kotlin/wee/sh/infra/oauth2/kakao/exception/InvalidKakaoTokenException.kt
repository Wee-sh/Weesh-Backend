package wee.sh.infra.oauth2.kakao.exception

import wee.sh.global.error.exception.ErrorCode
import wee.sh.global.error.exception.WeeShException

object InvalidKakaoTokenException : WeeShException(ErrorCode.INVALID_KAKAO_TOKEN)
