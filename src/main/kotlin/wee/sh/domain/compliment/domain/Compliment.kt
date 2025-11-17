package wee.sh.domain.compliment.domain

import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Id
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Column
import jakarta.persistence.ManyToOne
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import org.springframework.data.annotation.CreatedDate
import wee.sh.domain.user.domain.User
import java.time.LocalDateTime

@Entity
@Table(name = "tbl_compliments")
class Compliment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    val id: Long = 0L,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_user_id", nullable = false)
    val fromUser: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_user_id", nullable = false)
    val toUser: User,

    @Column(nullable = false, length = 200)
    val content: String,

    @Column(nullable = false)
    val isAnonymous: Boolean = false,

    @Column(nullable = false)
    val giftTemplate: Int,

    @CreatedDate
    @Column(nullable = false, updatable = false)
    var createdAt: LocalDateTime? = null
) {
    companion object {
        fun create(
            fromUser: User,
            toUser: User,
            content: String,
            isAnonymous: Boolean
        ): Compliment {
            return Compliment(
                fromUser = fromUser,
                toUser = toUser,
                content = content,
                isAnonymous = isAnonymous,
                giftTemplate = (1..5).random()
            )
        }
    }
}
