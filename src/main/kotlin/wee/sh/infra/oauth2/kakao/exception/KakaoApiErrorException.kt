package wee.sh.infra.oauth2.kakao.exception

import wee.sh.global.error.exception.ErrorCode
import wee.sh.global.error.exception.WeeShException

object KakaoApiErrorException : WeeShException(ErrorCode.KAKAO_API_ERROR)
