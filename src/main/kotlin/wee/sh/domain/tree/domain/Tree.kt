package wee.sh.domain.tree.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.GenerationType
import jakarta.persistence.Column
import jakarta.persistence.OneToOne
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import wee.sh.domain.user.domain.User
import java.util.UUID

@Entity
@Table(name = "tbl_tree")
class Tree(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    val id: Long = 0L,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    val user: User,

    @Column(nullable = false)
    var starCount: Int = 0,

    @Column(nullable = false)
    var giftBoxCount: Int = 0,

    @Column(nullable = false, unique = true)
    val shareToken: String = UUID.randomUUID().toString()
) {
    /**
     * 덕담 받을 때 증가
     * - 별 1 증가
     * - 선물상자 1 증가 (최대 5개)
     */
    fun receiveCompliment() {
        increaseStar()
        increaseGiftBox()
    }

    private fun increaseStar() {
        starCount++
    }

    private fun increaseGiftBox() {
        if (giftBoxCount >= 5) {
            return
        }
        giftBoxCount++
    }
}
