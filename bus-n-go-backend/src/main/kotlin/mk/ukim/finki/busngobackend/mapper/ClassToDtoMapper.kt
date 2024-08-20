package mk.ukim.finki.busngobackend.mapper

import mk.ukim.finki.busngobackend.api.responses.*
import mk.ukim.finki.busngobackend.domain.entities.*
import org.springframework.stereotype.Service

@Service
class ClassToDtoMapper {
    fun toUserResponse(user: Korisnik): UserResponse =
        UserResponse(
            id = user.id,
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
            ticket = this.toTicketResponse(it.bilet),
            id = it.id,
        )

    fun toControlResponse(kontrola: Kontrola) =
        ControlResponse(
            id = kontrola.id,
            instancaNaLinija = this.toRouteInstanceResponse(kontrola.instancaNaLinija),
            kondukter = this.toUserResponse(kontrola.kondukter.vraboten.korisnik),
            dateCreated = kontrola.dateCreated,
        )

    fun toFineResponse(
        kazna: Kazna,
        kzr: KaznaZaRegistriran?,
        kzn: KaznaZaNeregistriran?,
    ) = FineResponse(
        dokument = kazna.dokument,
        kontrola = this.toControlResponse(kazna.kontrola),
        plateno = kazna.plateno,
        iznos = kazna.iznos,
        id = kazna.id,
        kondukter = this.toUserResponse(kazna.kondukter.vraboten.korisnik),
        datePayed = kazna.datePayed,
        dateCreated = kazna.dateCreated,
        adresa = kzn?.adresa,
        patnik = kzr?.patnik?.let { this.toUserResponse(it.korisnik) },
        ime = kzn?.ime,
        telefon = kzn?.telefon,
    )

    fun toAdminUsersResponse(user: Korisnik): AdminUsersResponse =
        AdminUsersResponse(
            id = user.id,
            email = user.email,
            phoneNumber = user.telefon,
            address = user.adresa,
            name = user.ime,
            roles = user.roles!!.map { it.role!!.name },
        )
}
