package wee.sh.domain.compliment.domain.repository

import org.springframework.data.repository.CrudRepository
import wee.sh.domain.compliment.domain.Compliment

interface ComplimentRepository : CrudRepository<Compliment, Long> {
    fun findAllByToUserId(userId: Long): List<Compliment>
    fun findAllByFromUserId(userId: Long): List<Compliment>
}
