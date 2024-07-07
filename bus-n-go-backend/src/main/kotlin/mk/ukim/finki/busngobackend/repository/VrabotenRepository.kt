package mk.ukim.finki.busngobackend.repository

import mk.ukim.finki.busngobackend.domain.entities.Korisnik
import mk.ukim.finki.busngobackend.domain.entities.Vraboten
import org.springframework.data.jpa.repository.JpaRepository

interface VrabotenRepository : JpaRepository<Vraboten, Long> {
    fun findByKorisnik(korisnik: Korisnik): Vraboten?
}
