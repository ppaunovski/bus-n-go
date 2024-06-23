package mk.ukim.finki.busngobackend.mapper

import mk.ukim.finki.busngobackend.api.responses.UserResponse
import mk.ukim.finki.busngobackend.domain.entities.Korisnik
import org.springframework.stereotype.Service

@Service
class ClassToDtoMapper {
    fun toUserResponse(user: Korisnik): UserResponse =
        UserResponse(
            email = user.email,
            phoneNumber = user.telefon,
            address = user.adresa,
            name = user.ime,
        )
}
