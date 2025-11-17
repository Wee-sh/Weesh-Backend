package wee.sh.domain.tree.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Id
import wee.sh.domain.user.domain.User

@Entity
class Tree(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @Column(nullable = false)
    var starCount: Int = 0,

    @Column(nullable = false)
    var giftBoxCount: Int = 0
) {

    /**
     * 덕담 받을 때 증가
     * 별 1 증가
     * 선물상자 1 증가 (최대 5)
     */
    fun receiveCompliment() {
        increaseStar()
        increaseGiftBox()
    }

    private fun increaseStar() {
        starCount += 1
    }

    private fun increaseGiftBox() {
        if (giftBoxCount >= 5) {
            return
        }
        giftBoxCount += 1
    }
}
