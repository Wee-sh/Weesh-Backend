package wee.sh.domain.user.domain.repository

import org.springframework.data.jpa.repository.JpaRepository
import wee.sh.domain.user.domain.User

interface UserRepository : JpaRepository<User, Long> {
    fun findByKakaoId(kakaoId: String): User?
    fun existsByKakaoId(kakaoId: String): Boolean
}
