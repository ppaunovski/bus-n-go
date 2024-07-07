package mk.ukim.finki.busngobackend.repository

import mk.ukim.finki.busngobackend.domain.entities.Linija
import mk.ukim.finki.busngobackend.domain.entities.LinijaPravec
import mk.ukim.finki.busngobackend.domain.entities.Pravec
import org.springframework.data.jpa.repository.JpaRepository

interface LinijaPravecRepository : JpaRepository<LinijaPravec, Long> {
    fun findAllByLinija(linija: Linija): List<LinijaPravec>

    fun existsByLinijaAndPravec(
        linija: Linija,
        pravec: Pravec,
    ): Boolean
}
