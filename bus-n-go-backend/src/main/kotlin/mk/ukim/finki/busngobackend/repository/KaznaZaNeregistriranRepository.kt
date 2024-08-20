package mk.ukim.finki.busngobackend.repository

import mk.ukim.finki.busngobackend.domain.entities.Kazna
import mk.ukim.finki.busngobackend.domain.entities.KaznaZaNeregistriran
import org.springframework.data.jpa.repository.JpaRepository

interface KaznaZaNeregistriranRepository : JpaRepository<KaznaZaNeregistriran, Long> {
    fun findAllByKaznaIn(kazni: List<Kazna>): List<KaznaZaNeregistriran>
}
