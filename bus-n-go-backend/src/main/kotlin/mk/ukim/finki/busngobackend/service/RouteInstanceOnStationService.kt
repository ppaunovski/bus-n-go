package mk.ukim.finki.busngobackend.service

import mk.ukim.finki.busngobackend.domain.entities.InstancaNaLinijaPostojkaNaLinija
import mk.ukim.finki.busngobackend.repository.InstancaNaLinijaPostojkaNaLinijaRepository
import mk.ukim.finki.busngobackend.repository.InstancaNaLinijaRepository
import mk.ukim.finki.busngobackend.repository.PostojkaNaLinijaRepository
import mk.ukim.finki.busngobackend.service.exceptions.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.sql.Timestamp

@Service
class RouteInstanceOnStationService(
    private val instancaNaLinijaPostojkaNaLinijaRepository: InstancaNaLinijaPostojkaNaLinijaRepository,
    private val instancaNaLinijaRepository: InstancaNaLinijaRepository,
    private val postojkaNaLinijaRepository: PostojkaNaLinijaRepository,
) {
    fun create(
        routeInstanceId: Long,
        lineStationId: Long,
        timestamp: Timestamp,
    ): InstancaNaLinijaPostojkaNaLinija {
        val routeInstance =
            instancaNaLinijaRepository.findByIdOrNull(routeInstanceId) ?: throw NotFoundException("Route instance not found")
        val lineStation = postojkaNaLinijaRepository.findByIdOrNull(lineStationId) ?: throw NotFoundException("Line station not found")
        return instancaNaLinijaPostojkaNaLinijaRepository.save(
            InstancaNaLinijaPostojkaNaLinija(
                instancaNaLinija = routeInstance,
                postojkaNaLinija = lineStation,
                timestamp = timestamp,
                id = 0L,
            ),
        )
    }
}
