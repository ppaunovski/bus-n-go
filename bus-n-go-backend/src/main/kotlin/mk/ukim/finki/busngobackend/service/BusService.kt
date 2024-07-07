package mk.ukim.finki.busngobackend.service

import mk.ukim.finki.busngobackend.domain.entities.Avtobus
import mk.ukim.finki.busngobackend.repository.AvtobusRepository
import org.springframework.stereotype.Service

@Service
class BusService(
    private val avtobusRepository: AvtobusRepository,
) {
    fun findAll() = avtobusRepository.findAll()

    fun findAllFree(): List<Avtobus> = this.avtobusRepository.findAllFreeBuses()
}
