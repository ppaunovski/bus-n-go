package mk.ukim.finki.busngobackend.service

import mk.ukim.finki.busngobackend.api.requests.AuthRequest
import mk.ukim.finki.busngobackend.api.requests.RegisterRequest
import mk.ukim.finki.busngobackend.api.responses.AuthResponse
import mk.ukim.finki.busngobackend.config.JwtService
import mk.ukim.finki.busngobackend.domain.entities.Korisnik
import mk.ukim.finki.busngobackend.domain.entities.KorisnikRole
import mk.ukim.finki.busngobackend.repository.KorisnikRepository
import mk.ukim.finki.busngobackend.repository.KorisnikRoleRepository
import mk.ukim.finki.busngobackend.repository.RoleRepository
import mk.ukim.finki.busngobackend.service.exceptions.UnauthorizedAccessException
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val authenticationManager: AuthenticationManager,
    private val userDetailsService: UserDetailsService,
    private val jwtService: JwtService,
    private val korisnikRepository: KorisnikRepository,
    private val korisnikRoleRepository: KorisnikRoleRepository,
    private val roleRepository: RoleRepository,
) {
    fun getSecurityContext(): SecurityContext = SecurityContextHolder.getContext()

    fun isAuthenticated(): Boolean {
        if (getSecurityContext().authentication == null) {
            throw UnauthorizedAccessException("User is not authenticated")
        }
        return true
    }

    fun register(registerRequest: RegisterRequest): AuthResponse {
        if (this.korisnikRepository.findByEmail(registerRequest.email) != null) {
            throw RuntimeException("Invalid credentials")
        }
        if (registerRequest.password != registerRequest.confirmPassword) {
            throw RuntimeException("Passwords do not match")
        }

        val roles = this.roleRepository.findAllByNameLikeIgnoreCase("passenger")

        val user =
            this.korisnikRepository.save(
                Korisnik(
                    email = registerRequest.email,
                    lozinka = registerRequest.password,
                    ime = registerRequest.name,
                    id = 4,
                    adresa = registerRequest.address,
                    admin = false,
                    telefon = registerRequest.phoneNumber,
                    roles = null,
                ),
            )

        val newRoles =
            roles.map {
                this.korisnikRoleRepository.save(
                    KorisnikRole(
                        id = 0,
                        role = it,
                        korisnik = user,
                    ),
                )
            }

        user.roles = newRoles

        this.korisnikRepository.save(user)

        return this.authenticate(AuthRequest(user.email, user.lozinka))
    }

    fun authenticate(authRequest: AuthRequest): AuthResponse {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authRequest.email,
                authRequest.password,
            ),
        )

        val user = userDetailsService.loadUserByUsername(authRequest.email)

        val token = jwtService.generateToken(userDetails = user)

        return AuthResponse(token)
    }
}
