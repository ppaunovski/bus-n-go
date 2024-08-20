package mk.ukim.finki.busngobackend.service

import mk.ukim.finki.busngobackend.api.responses.ControlResponse
import mk.ukim.finki.busngobackend.api.responses.FineResponse
import mk.ukim.finki.busngobackend.domain.entities.Kontrola
import mk.ukim.finki.busngobackend.mapper.ClassToDtoMapper
import mk.ukim.finki.busngobackend.repository.*
import mk.ukim.finki.busngobackend.service.exceptions.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.time.LocalDateTime

@Service
class ControlService(
    private val kontrolaRepository: KontrolaRepository,
    private val dtoMapper: ClassToDtoMapper,
    private val authService: AuthService,
    private val instancaNaLinijaRepository: InstancaNaLinijaRepository,
    private val kaznaRepository: KaznaRepository,
    private val kaznaZaRegistriranRepository: KaznaZaRegistriranRepository,
    private val kaznaZaNeregistriranRepository: KaznaZaNeregistriranRepository,
) {
    fun getAllByConductor(): List<ControlResponse> {
        val kondukter = authService.getConductor()

        return kontrolaRepository
            .findAllByKondukterOrderByDateCreatedDesc(
                kondukter,
            ).map { kontrola -> dtoMapper.toControlResponse(kontrola) }
    }

    fun start(routeInstanceId: Long): ControlResponse {
        val instancaNaLinija =
            instancaNaLinijaRepository.findByIdOrNull(routeInstanceId) ?: throw NotFoundException("Route instance")
        val kontrola =
            kontrolaRepository.save(
                Kontrola(
                    id = 0L,
                    kondukter = authService.getConductor(),
                    dateCreated = Timestamp.valueOf(LocalDateTime.now()),
                    instancaNaLinija = instancaNaLinija,
                ),
            )
        return kontrola.let { dtoMapper.toControlResponse(it) }
    }

    fun getFines(controlId: Long): List<FineResponse> {
        val kondukter = authService.getConductor()
        val kontrola = kontrolaRepository.findByIdOrNull(controlId) ?: throw NotFoundException("Control not found")

        val kazni = kaznaRepository.findAllByKontrola(kontrola)

        val kzrs = kaznaZaRegistriranRepository.findAllByKaznaIn(kazni)
        val kzns = kaznaZaNeregistriranRepository.findAllByKaznaIn(kazni)

        return kzrs
            .map { dtoMapper.toFineResponse(kazna = it.kazna, kzr = it, kzn = null) }
            .plus(kzns.map { dtoMapper.toFineResponse(kazna = it.kazna, kzr = null, kzn = it) })
    }
}
