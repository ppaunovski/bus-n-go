package mk.ukim.finki.busngobackend.repository

import mk.ukim.finki.busngobackend.domain.entities.Kazna
import mk.ukim.finki.busngobackend.domain.entities.KaznaZaRegistriran
import org.springframework.data.jpa.repository.JpaRepository

interface KaznaZaRegistriranRepository : JpaRepository<KaznaZaRegistriran, Long> {
    fun findAllByKaznaIn(kazni: List<Kazna>): List<KaznaZaRegistriran>
}
