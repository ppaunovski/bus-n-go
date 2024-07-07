package mk.ukim.finki.busngobackend.repository

import mk.ukim.finki.busngobackend.domain.entities.Korisnik
import mk.ukim.finki.busngobackend.domain.entities.Patnik
import org.springframework.data.jpa.repository.JpaRepository

interface PatnikRepository : JpaRepository<Patnik, Long> {
    fun findByKorisnik(korisnik: Korisnik): Patnik?
}
