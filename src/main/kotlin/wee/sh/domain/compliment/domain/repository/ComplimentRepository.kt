package wee.sh.domain.compliment.domain.repository

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import wee.sh.domain.compliment.domain.Compliment
import wee.sh.domain.user.domain.projection.UserRankProjection

interface ComplimentRepository : CrudRepository<Compliment, Long> {
    fun findAllByToUserId(userId: Long): List<Compliment>
    fun findAllByFromUserId(userId: Long): List<Compliment>
    @Query("""
    SELECT u.id AS userId,
           u.nickname AS userNickname,
           COUNT(c.id) AS sentCount
    FROM Compliment c
    JOIN c.fromUser u
    GROUP BY u.id, u.nickname
    ORDER BY sentCount DESC
""")
    fun findUserRank(): List<UserRankProjection>
}
