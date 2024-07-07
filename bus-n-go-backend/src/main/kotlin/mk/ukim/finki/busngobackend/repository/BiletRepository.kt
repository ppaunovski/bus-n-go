package mk.ukim.finki.busngobackend.repository

import mk.ukim.finki.busngobackend.domain.entities.Bilet
import mk.ukim.finki.busngobackend.domain.entities.Patnik
import org.springframework.data.jpa.repository.JpaRepository

interface BiletRepository : JpaRepository<Bilet, Long> {
    fun findAllByPatnik(patnik: Patnik): List<Bilet>

    fun findByIdAndPatnik(
        id: Long,
        patnik: Patnik,
    ): Bilet?
}
