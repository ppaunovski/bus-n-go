package mk.ukim.finki.busngobackend.service

import mk.ukim.finki.busngobackend.api.responses.UserResponse
import mk.ukim.finki.busngobackend.mapper.ClassToDtoMapper
import mk.ukim.finki.busngobackend.repository.KorisnikRepository
import mk.ukim.finki.busngobackend.service.exceptions.UnauthorizedAccessException
import org.springframework.stereotype.Service

@Service
class KorisnikService(
    private val korisnikRepository: KorisnikRepository,
    private val authService: AuthService,
    private val mapper: ClassToDtoMapper,
) {
    fun getAuthenticatedUser(): UserResponse {
        val user =
            authService.getSecurityContext().authentication?.let { auth ->
                korisnikRepository.findByEmail(auth.name)
            }

        return user?.let { mapper.toUserResponse(it) } ?: throw UnauthorizedAccessException("Unauthorized access")
    }
}
