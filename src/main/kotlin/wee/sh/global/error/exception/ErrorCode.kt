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
    BAD_REQUEST(400, "Bad Request"),

    // jwt
    UNAUTHORIZED(401, "Unauthorized"),
    INVALID_TOKEN(401, "Invalid Token"),
    EXPIRED_TOKEN(401, "Expired Token"),

    // kakao oauth
    KAKAO_API_ERROR(500, "Kakao API Error"),
    INVALID_KAKAO_TOKEN(401, "Invalid Kakao Token"),
    KAKAO_USER_INFO_NULL(500, "Kakao User Info Null"),

    // user
    USER_NOT_FOUND(404, "User not found"),

    // tree
    TREE_NOT_FOUND(404, "Tree not found"),

    // compliment
    SELF_COMPLIMENT_NOT_ALLOWED(400, "Self compliment not allowed"),
    ALREADY_COMPLIMENTED(409, "Already compliment")
    ;
}
