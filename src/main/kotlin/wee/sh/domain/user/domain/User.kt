package wee.sh.domain.user.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.GenerationType
import jakarta.persistence.Column
import java.time.LocalDateTime


@Entity
@Table(name = "tbl_user")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    var id: Long = 0,

    @Column(nullable = false, unique = true)
    var kakaoId: String,

    @Column(nullable = false)
    var nickname: String,

    @Column(nullable = false)
    var nicknameChanged: Boolean = false,

    @Column(nullable = false)
    val templateId: Int,

    @Column
    var deletedAt: LocalDateTime? = null
) {
    fun updateNickname(newNickname: String) {
        require(!nicknameChanged) { "닉네임은 1회만 변경 가능합니다." }
        require(newNickname.length in 1..20) { "닉네임은 1~20자 이내여야 합니다." }

        this.nickname = newNickname
        this.nicknameChanged = true
    }

    fun softDelete() {
        this.deletedAt = LocalDateTime.now()
    }

    fun isDeleted(): Boolean = deletedAt != null

    companion object {
        fun create(kakaoId: String, nickname: String): User {
            return User(
                kakaoId = kakaoId,
                nickname = nickname,
                templateId = (1..5).random()
            )
        }
    }
}
