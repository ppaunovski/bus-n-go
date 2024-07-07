package mk.ukim.finki.busngobackend.repository

import mk.ukim.finki.busngobackend.domain.entities.Vozac
import mk.ukim.finki.busngobackend.domain.entities.Vraboten
import org.springframework.data.jpa.repository.JpaRepository

interface VozacRepository : JpaRepository<Vozac, Long> {
    fun findByVraboten(vraboten: Vraboten): Vozac?
}
