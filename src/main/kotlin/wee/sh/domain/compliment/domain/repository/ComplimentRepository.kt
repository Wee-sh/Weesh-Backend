package wee.sh.domain.compliment.domain.repository

import org.springframework.data.repository.CrudRepository
import wee.sh.domain.compliment.domain.Compliment

interface ComplimentRepository : CrudRepository<Compliment, Long>
