package wee.sh.global.error.exception

abstract class WeeShException(
    val errorCode: ErrorCode
) : RuntimeException()
