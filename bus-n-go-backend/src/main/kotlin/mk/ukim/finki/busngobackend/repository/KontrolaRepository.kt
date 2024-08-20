package mk.ukim.finki.busngobackend.repository

import mk.ukim.finki.busngobackend.domain.entities.Kondukter
import mk.ukim.finki.busngobackend.domain.entities.Kontrola
import org.springframework.data.jpa.repository.JpaRepository

interface KontrolaRepository : JpaRepository<Kontrola, Long> {
    fun findAllByKondukterOrderByDateCreatedDesc(kondukter: Kondukter): List<Kontrola>
}
