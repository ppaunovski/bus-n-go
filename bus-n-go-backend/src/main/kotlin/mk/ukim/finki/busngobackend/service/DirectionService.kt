package mk.ukim.finki.busngobackend.service

import mk.ukim.finki.busngobackend.domain.entities.LinijaPravec
import mk.ukim.finki.busngobackend.repository.LinijaPravecRepository
import mk.ukim.finki.busngobackend.repository.LinijaRepository
import mk.ukim.finki.busngobackend.repository.PravecRepository
import mk.ukim.finki.busngobackend.service.exceptions.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class DirectionService(
    private val pravecRepository: PravecRepository,
    private val linijaPravecRepository: LinijaPravecRepository,
    private val linijaRepository: LinijaRepository,
) {
    fun findDirectionsByLineId(lineId: Int): List<LinijaPravec> {
        val linija = linijaRepository.findByIdOrNull(lineId) ?: throw NotFoundException("Line not found")
        return linijaPravecRepository.findAllByLinija(linija)
    }
}
