package mk.ukim.finki.busngobackend.service

import mk.ukim.finki.busngobackend.domain.entities.Linija
import mk.ukim.finki.busngobackend.mapper.ClassToDtoMapper
import mk.ukim.finki.busngobackend.repository.LinijaRepository
import org.springframework.stereotype.Service

@Service
class LineService(
    private val linijaRepository: LinijaRepository,
    private val dtoMapper: ClassToDtoMapper,
) {
    fun findAllLines(): List<Linija> = this.linijaRepository.findAll()
}
