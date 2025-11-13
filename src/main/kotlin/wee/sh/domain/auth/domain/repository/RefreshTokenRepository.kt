package wee.sh.domain.auth.domain.repository

import org.springframework.data.repository.CrudRepository
import wee.sh.domain.auth.domain.RefreshToken

interface RefreshTokenRepository : CrudRepository<RefreshToken, String> {
}
