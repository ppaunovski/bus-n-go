package mk.ukim.finki.busngobackend.service

import jakarta.transaction.Transactional
import mk.ukim.finki.busngobackend.api.requests.StartCommuteRequest
import mk.ukim.finki.busngobackend.api.responses.CommuteResponse
import mk.ukim.finki.busngobackend.domain.entities.Vozenje
import mk.ukim.finki.busngobackend.domain.enums.BiletEnum
import mk.ukim.finki.busngobackend.domain.enums.RoleEnum
import mk.ukim.finki.busngobackend.domain.enums.VozenjeStatus
import mk.ukim.finki.busngobackend.mapper.ClassToDtoMapper
import mk.ukim.finki.busngobackend.repository.*
import mk.ukim.finki.busngobackend.service.exceptions.NotFoundException
import mk.ukim.finki.busngobackend.service.exceptions.UnauthorizedAccessException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.time.LocalDateTime

@Service
class CommuteService(
    private val linijaRepository: LinijaRepository,
    private val postojkaRepository: PostojkaRepository,
    private val postojkaNaLinijaRepository: PostojkaNaLinijaRepository,
    private val biletRepository: BiletRepository,
    private val authService: AuthService,
    private val patnikRepository: PatnikRepository,
    private val instancaNaLinijaRepository: InstancaNaLinijaRepository,
    private val vozenjeRepository: VozenjeRepository,
    private val dtoMapper: ClassToDtoMapper,
) {
    @Transactional
    fun start(request: StartCommuteRequest): CommuteResponse {
        if (!this.authService.hasAuthority(RoleEnum.ROLE_PASSENGER)) throw UnauthorizedAccessException("Unauthorised role")
        val korisnik = this.authService.getAuthenticatedUser()
        val patnik = patnikRepository.findByKorisnik(korisnik) ?: throw NotFoundException("Korisnik")

        var bilet = biletRepository.findByIdAndPatnik(request.ticketId, patnik) ?: throw NotFoundException("Bilet")

        val instancaNaLinija =
            instancaNaLinijaRepository.findByIdOrNull(request.routeInstanceId) ?: throw NotFoundException("RouteInstanceId")
        val postojka = postojkaRepository.findByIdOrNull(request.stationId) ?: throw NotFoundException("StationId")

        val postojkaNaLinijaStart =
            postojkaNaLinijaRepository.findByLinijaAndPostojkaAndPravec(instancaNaLinija.linija, postojka, instancaNaLinija.pravec)
                ?: throw NotFoundException("Station not found")

        if (bilet.status == BiletEnum.INACTIVE) {
            bilet.status = BiletEnum.ACTIVE
            bilet.datumAktivacija = Timestamp.valueOf(LocalDateTime.now())
            bilet = biletRepository.save(bilet)
        }

        val vozenje =
            Vozenje(
                id = 0L,
                startDate = Timestamp.valueOf(LocalDateTime.now()),
                endDate = null,
                status = VozenjeStatus.ACTIVE,
                patnik = patnik,
                postojkaNaLinijaStart = postojkaNaLinijaStart,
                instancaNaLinija = instancaNaLinija,
                bilet = bilet,
            )
        return vozenjeRepository.save(vozenje).let { dtoMapper.toCommuteResponse(it) }
    }
}
