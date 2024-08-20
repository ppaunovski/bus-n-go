package mk.ukim.finki.busngobackend.service

import mk.ukim.finki.busngobackend.api.responses.UserResponse
import mk.ukim.finki.busngobackend.domain.entities.Korisnik
import mk.ukim.finki.busngobackend.mapper.ClassToDtoMapper
import mk.ukim.finki.busngobackend.repository.KorisnikRepository
import mk.ukim.finki.busngobackend.repository.PatnikRepository
import mk.ukim.finki.busngobackend.service.exceptions.UnauthorizedAccessException
import org.springframework.stereotype.Service

@Service
class KorisnikService(
    private val korisnikRepository: KorisnikRepository,
    private val authService: AuthService,
    private val mapper: ClassToDtoMapper,
    private val patnikRepository: PatnikRepository,
    private val classToDtoMapper: ClassToDtoMapper,
) {
    fun getAuthenticatedUser(): UserResponse {
        val user =
            authService.getSecurityContext().authentication?.let { auth ->
                korisnikRepository.findByEmail(auth.name)
            }

        return user?.let { mapper.toUserResponse(it) } ?: throw UnauthorizedAccessException("Unauthorized access")
    }

    fun getUser(): Korisnik =
        authService.getSecurityContext().authentication?.let { auth ->
            korisnikRepository.findByEmail(auth.name)
        } ?: throw UnauthorizedAccessException("Unauthorized access")

    fun getAllPassengers(): List<UserResponse> = patnikRepository.findAll().map { classToDtoMapper.toUserResponse(it.korisnik) }

    fun getAllUsers() = korisnikRepository.findAll().map { classToDtoMapper.toAdminUsersResponse(it) }
}
