package mk.ukim.finki.busngobackend.service

import jakarta.transaction.Transactional
import mk.ukim.finki.busngobackend.api.requests.FineRequest
import mk.ukim.finki.busngobackend.api.responses.FineResponse
import mk.ukim.finki.busngobackend.domain.entities.Kazna
import mk.ukim.finki.busngobackend.domain.entities.KaznaZaNeregistriran
import mk.ukim.finki.busngobackend.domain.entities.KaznaZaRegistriran
import mk.ukim.finki.busngobackend.mapper.ClassToDtoMapper
import mk.ukim.finki.busngobackend.repository.*
import mk.ukim.finki.busngobackend.service.exceptions.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.time.LocalDateTime

@Service
class FineService(
    private val kaznaRepository: KaznaRepository,
    private val authService: AuthService,
    private val kontrolaRepository: KontrolaRepository,
    private val dtoMapper: ClassToDtoMapper,
    private val kaznaZaRegistriranRepository: KaznaZaRegistriranRepository,
    private val kaznaZaNeregistriranRepository: KaznaZaNeregistriranRepository,
    private val korisnikRepository: KorisnikRepository,
    private val patnikRepository: PatnikRepository,
) {
    @Transactional
    fun createFine(request: FineRequest): FineResponse {
        val kontrola = kontrolaRepository.findByIdOrNull(request.kontrolaId) ?: throw NotFoundException("Kontrola")
        val now = Timestamp.valueOf(LocalDateTime.now())
        val kazna =
            kaznaRepository.save(
                Kazna(
                    dateCreated = now,
                    id = 0L,
                    kondukter = authService.getConductor(),
                    datePayed =
                        if (request.plateno) {
                            now
                        } else {
                            null
                        },
                    iznos = request.iznos,
                    dokument = request.dokument,
                    kontrola = kontrola,
                    plateno = request.plateno,
                ),
            )

        if (request.patnikId != null) {
            val korisnik = korisnikRepository.findByIdOrNull(request.patnikId!!) ?: throw NotFoundException("Korisnik")
            val patnik = patnikRepository.findByKorisnik(korisnik) ?: throw NotFoundException("Patnik")
            val kzr =
                kaznaZaRegistriranRepository.save(
                    KaznaZaRegistriran(
                        patnik = patnik,
                        id = 0L,
                        kazna = kazna,
                    ),
                )
            return kzr.let { dtoMapper.toFineResponse(kazna = kazna, kzr = kzr, kzn = null) }
        }

        if (request.adresa != null && request.ime != null && request.telefon != null) {
            val kzn =
                kaznaZaNeregistriranRepository.save(
                    KaznaZaNeregistriran(
                        id = 0L,
                        kazna = kazna,
                        ime = request.ime!!,
                        telefon = request.telefon!!,
                        adresa = request.adresa!!,
                    ),
                )

            return kzn.let { dtoMapper.toFineResponse(kazna = kazna, kzn = kzn, kzr = null) }
        }
        return dtoMapper.toFineResponse(kazna = kazna, kzr = null, kzn = null)
    }
}
