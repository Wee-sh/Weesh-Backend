package wee.sh.global.error.exception

import com.fasterxml.jackson.annotation.JsonFormat

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
enum class ErrorCode (
    val status: Int,
    val message: String
) {
    INTERNAL_SERVER_ERROR(500,"Internal Server Error"),
    ;
}
