package wee.sh.infra.oauth2.kakao.exception

import wee.sh.global.error.exception.ErrorCode
import wee.sh.global.error.exception.WeeShException

object KakaoUserInfoNullException : WeeShException(ErrorCode.KAKAO_USER_INFO_NULL)
