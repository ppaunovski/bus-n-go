package mk.ukim.finki.busngobackend.service

import mk.ukim.finki.busngobackend.domain.entities.Postojka
import mk.ukim.finki.busngobackend.domain.entities.PostojkaNaLinija
import mk.ukim.finki.busngobackend.repository.LinijaRepository
import mk.ukim.finki.busngobackend.repository.PostojkaNaLinijaRepository
import mk.ukim.finki.busngobackend.repository.PostojkaRepository
import mk.ukim.finki.busngobackend.repository.PravecRepository
import mk.ukim.finki.busngobackend.service.exceptions.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class StationService(
    private val postojkaRepository: PostojkaRepository,
    private val postojkaNaLinijaRepository: PostojkaNaLinijaRepository,
    private val linijaRepository: LinijaRepository,
    private val pravecRepository: PravecRepository,
) {
    fun findStationsByLineId(lineId: Int): List<PostojkaNaLinija> {
        val linija = linijaRepository.findByIdOrNull(lineId) ?: throw NotFoundException("Linija not found")
        return this.postojkaNaLinijaRepository.findByLinija(linija)
    }

    fun findStationsByLineIdAndDirectionId(
        lineId: Int,
        directionId: Long?,
    ): List<PostojkaNaLinija> {
        if (directionId == null) {
            return this.findStationsByLineId(lineId)
        }

        val linija = linijaRepository.findByIdOrNull(lineId) ?: throw NotFoundException("Linija not found")
        val pravec = pravecRepository.findByIdOrNull(directionId) ?: throw NotFoundException("Pravec not found")

        return postojkaNaLinijaRepository.findByLinijaAndPravec(linija, pravec)
    }

    fun getAll(): List<Postojka> = this.postojkaRepository.findAll()
}
