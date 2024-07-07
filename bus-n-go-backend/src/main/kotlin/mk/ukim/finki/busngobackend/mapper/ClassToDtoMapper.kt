package mk.ukim.finki.busngobackend.mapper

import mk.ukim.finki.busngobackend.api.responses.CommuteResponse
import mk.ukim.finki.busngobackend.api.responses.RouteInstanceResponse
import mk.ukim.finki.busngobackend.api.responses.TicketResponse
import mk.ukim.finki.busngobackend.api.responses.UserResponse
import mk.ukim.finki.busngobackend.domain.entities.Bilet
import mk.ukim.finki.busngobackend.domain.entities.InstancaNaLinija
import mk.ukim.finki.busngobackend.domain.entities.Korisnik
import mk.ukim.finki.busngobackend.domain.entities.Vozenje
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

    fun toTicketResponse(it: Bilet) =
        TicketResponse(
            id = it.id,
            tip = it.tip,
            datumKupuvanje = it.datumKupuvanje.toLocalDateTime(),
            status = it.status.name,
            datumAktivacija = it.datumAktivacija?.toLocalDateTime(),
        )

    fun toRouteInstanceResponse(it: InstancaNaLinija) =
        RouteInstanceResponse(
            line = it.linija,
            bus = it.avtobus,
            start = it.startDate.toLocalDateTime(),
            driver = toUserResponse(it.vozac.vraboten.korisnik),
            direction = it.pravec,
            end = it.endDate?.toLocalDateTime(),
            id = it.id,
        )

    fun toCommuteResponse(it: Vozenje) =
        CommuteResponse(
            status = it.status.name,
            endDate = it.endDate?.toLocalDateTime(),
            startDate = it.startDate.toLocalDateTime(),
            commuter = this.toUserResponse(it.patnik.korisnik),
            stationStart = it.postojkaNaLinijaStart,
            ticket = it.bilet,
            id = it.id,
        )
}
