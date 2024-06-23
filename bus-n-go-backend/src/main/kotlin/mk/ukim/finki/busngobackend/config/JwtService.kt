package mk.ukim.finki.busngobackend.config

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.security.Key
import java.util.Date

@Service
class JwtService {
    private val secretKey =
        Keys.hmacShaKeyFor(
            "hu55nfa3pRrBRV4DIlvKVDMrg7jnqla9".toByteArray(),
        )

    fun extractUsername(token: String): String? = extractClaim(token, Claims::getSubject)

    fun <T> extractClaim(
        token: String,
        claimsResolver: (Claims) -> T,
    ): T {
        val claims = extractAllClaims(token)
        return claimsResolver(claims)
    }

    fun generateToken(
        extraClaims: Map<String, Any> = emptyMap(),
        userDetails: UserDetails,
    ): String =
        Jwts
            .builder()
            .claims()
            .subject(userDetails.username)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
            .add(extraClaims)
            .and()
            .signWith(getSignInKey())
            .compact()

    fun isTokenValid(
        token: String,
        userDetails: UserDetails,
    ): Boolean {
        val username = extractUsername(token)
        return username == userDetails.username && !isTokenExpired(token)
    }

    private fun isTokenExpired(token: String): Boolean = extractExpiration(token).before(Date())

    private fun extractExpiration(token: String): Date = extractClaim(token, Claims::getExpiration)

    private fun extractAllClaims(token: String): Claims =
        Jwts
            .parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .payload

    private fun getSignInKey(): Key = secretKey
}
