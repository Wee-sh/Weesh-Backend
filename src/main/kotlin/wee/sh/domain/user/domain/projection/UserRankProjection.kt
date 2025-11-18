package wee.sh.domain.user.domain.projection

interface UserRankProjection {
    val userId: Long
    val userNickname: String
    val sentCount: Long
}
