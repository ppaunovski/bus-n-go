package mk.ukim.finki.busngobackend.service

import mk.ukim.finki.busngobackend.api.requests.StartRouteInstanceRequest
import mk.ukim.finki.busngobackend.api.responses.RouteInstanceResponse
import mk.ukim.finki.busngobackend.domain.entities.InstancaNaLinija
import mk.ukim.finki.busngobackend.domain.enums.RoleEnum
import mk.ukim.finki.busngobackend.mapper.ClassToDtoMapper
import mk.ukim.finki.busngobackend.repository.*
import mk.ukim.finki.busngobackend.service.exceptions.NotFoundException
import mk.ukim.finki.busngobackend.service.exceptions.UnauthorizedAccessException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.time.LocalDateTime

@Service
class RouteInstanceService(
    private val instancaNaLinijaRepository: InstancaNaLinijaRepository,
    private val linijaRepository: LinijaRepository,
    private val pravecRepository: PravecRepository,
    private val linijaPravecRepository: LinijaPravecRepository,
    private val avtobusRepository: AvtobusRepository,
    private val authService: AuthService,
    private val vrabotenRepository: VrabotenRepository,
    private val vozacRepository: VozacRepository,
    private val dtoMapper: ClassToDtoMapper,
) {
    fun start(request: StartRouteInstanceRequest): RouteInstanceResponse {
        if (!authService.isAuthenticated() || !authService.hasAuthority(RoleEnum.ROLE_DRIVER)) {
            throw UnauthorizedAccessException("Unauthorized access")
        }

        val korisnik = authService.getAuthenticatedUser()
        val vraboten = vrabotenRepository.findByKorisnik(korisnik) ?: throw NotFoundException("Korisnik")
        val vozac = vozacRepository.findByVraboten(vraboten) ?: throw NotFoundException("Vraboten")

        val linija = linijaRepository.findByIdOrNull(request.lineId) ?: throw NotFoundException("Line not found")
        val pravec = pravecRepository.findByIdOrNull(request.directionId) ?: throw NotFoundException("Direction not found")

        if (!linijaPravecRepository.existsByLinijaAndPravec(linija, pravec)) {
            throw NotFoundException("Line does not has the specified direction")
        }

        val avtobus = avtobusRepository.findByIdOrNull(request.busId) ?: throw NotFoundException("Bus Not Found")

        val instanca =
            InstancaNaLinija(
                id = 0L,
                startDate = Timestamp.valueOf(LocalDateTime.now()),
                linija = linija,
                pravec = pravec,
                avtobus = avtobus,
                vozac = vozac,
                endDate = null,
            )

        return instancaNaLinijaRepository.save(instanca).let { dtoMapper.toRouteInstanceResponse(it) }
    }

    fun findById(id: Long): InstancaNaLinija = instancaNaLinijaRepository.findByIdOrNull(id) ?: throw NotFoundException("id not found")

    fun stop(id: Long): RouteInstanceResponse {
        val instance = this.findById(id)
        if (instance.endDate != null) throw UnauthorizedAccessException("Unauthorized access")
        instance.endDate = Timestamp.valueOf(LocalDateTime.now())
        return instancaNaLinijaRepository.save(instance).let { dtoMapper.toRouteInstanceResponse(it) }
    }

    fun getById(id: Long): RouteInstanceResponse {
        val ri = this.findById(id)
        val korisnik = this.authService.getAuthenticatedUser()
        if (korisnik.id != ri.vozac.vraboten.korisnik.id) throw UnauthorizedAccessException("Unauthorized access")
        return dtoMapper.toRouteInstanceResponse(this.findById(id))
    }
}
