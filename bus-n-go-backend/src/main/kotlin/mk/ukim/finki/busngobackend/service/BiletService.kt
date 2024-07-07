package mk.ukim.finki.busngobackend.service

import mk.ukim.finki.busngobackend.api.requests.TicketBuyRequest
import mk.ukim.finki.busngobackend.api.responses.TicketResponse
import mk.ukim.finki.busngobackend.domain.entities.Bilet
import mk.ukim.finki.busngobackend.domain.enums.BiletEnum
import mk.ukim.finki.busngobackend.mapper.ClassToDtoMapper
import mk.ukim.finki.busngobackend.repository.BiletRepository
import mk.ukim.finki.busngobackend.repository.PatnikRepository
import mk.ukim.finki.busngobackend.repository.TipbiletRepository
import mk.ukim.finki.busngobackend.service.exceptions.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.time.LocalDateTime

@Service
class BiletService(
    private val mapper: ClassToDtoMapper,
    private val biletRepository: BiletRepository,
    private val korisnikService: KorisnikService,
    private val patnikRepository: PatnikRepository,
    private val tipbiletRepository: TipbiletRepository,
) {
    fun getTicketsByUser(): List<TicketResponse> {
        val user = korisnikService.getUser()
        val patnik =
            patnikRepository.findByKorisnik(user) ?: throw NotFoundException("Patnik not found")

        return biletRepository.findAllByPatnik(patnik).map { mapper.toTicketResponse(it) }
    }

    fun buyTicket(request: TicketBuyRequest): TicketResponse {
        val user = korisnikService.getUser()
        val patnik =
            patnikRepository.findByKorisnik(user) ?: throw NotFoundException("Patnik not found")
        val type = tipbiletRepository.findByIdOrNull(request.type) ?: throw NotFoundException("Type not found")

        val ticket =
            biletRepository.save(
                Bilet(
                    id = 0L,
                    status = BiletEnum.INACTIVE,
                    tip = type,
                    datumKupuvanje = Timestamp.valueOf(LocalDateTime.now()),
                    patnik = patnik,
                    datumAktivacija = null,
                ),
            )

        return mapper.toTicketResponse(ticket)
    }

    fun activateTicket(id: Long): TicketResponse {
        val user = korisnikService.getUser()
        val patnik =
            patnikRepository.findByKorisnik(user) ?: throw NotFoundException("Patnik not found")

        val ticket = biletRepository.findByIdAndPatnik(id, patnik) ?: throw NotFoundException("Ticket not found")

        ticket.status = BiletEnum.ACTIVE
        ticket.datumAktivacija = Timestamp.valueOf(LocalDateTime.now())
        return mapper.toTicketResponse(biletRepository.save(ticket))
    }
}
