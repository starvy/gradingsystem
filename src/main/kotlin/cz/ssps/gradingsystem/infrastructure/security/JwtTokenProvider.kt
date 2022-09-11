package cz.ssps.gradingsystem.infrastructure.security

import io.smallrye.jwt.build.Jwt
import org.eclipse.microprofile.config.inject.ConfigProperty
import java.time.Instant.now
import java.time.temporal.ChronoUnit.MINUTES
import javax.enterprise.context.ApplicationScoped

/**
 * Json Web Token
 * See: https://quarkus.io/guides/security-jwt
 */
@ApplicationScoped
class JwtTokenProvider(
    @ConfigProperty(name = "mp.jwt.verify.issuer")
    private val issuer: String,
    @ConfigProperty(name = "mp.jwt.expiration.time.minutes")
    private val expirationTimeInMinutes: Long,
) {
    fun create(username: String): String = Jwt.claims()
        .issuer(issuer)
        .subject(username)
        .issuedAt(now())
        .expiresAt(now().plus(expirationTimeInMinutes, MINUTES))
        .groups(setOf(RoleType.USER))
        .sign()

    fun create(username: String, role: String): String {
        println("role $role")
        return Jwt.claims()
            .issuer(issuer)
            .subject(username)
            .issuedAt(now())
            .expiresAt(now().plus(expirationTimeInMinutes, MINUTES))
            .groups(setOf(role))
            .sign()
    }
}
