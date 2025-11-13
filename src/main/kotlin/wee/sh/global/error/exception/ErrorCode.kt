package wee.sh.global.error.exception

import com.fasterxml.jackson.annotation.JsonFormat

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
enum class ErrorCode(
    val status: Int,
    val message: String
) {
    // server error
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),

    // client error
    BadRequest(400, "Bad Request"),

    // jwt
    INVALID_TOKEN(401, "Invalid Token"),
    EXPIRED_TOKEN(401, "Expired Token"),
    ;
}
