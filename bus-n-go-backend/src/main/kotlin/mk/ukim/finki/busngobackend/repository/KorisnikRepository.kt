package mk.ukim.finki.busngobackend.repository

import mk.ukim.finki.busngobackend.domain.entities.Korisnik
import org.springframework.data.jpa.repository.JpaRepository

interface KorisnikRepository : JpaRepository<Korisnik, Long> {
    fun findByEmail(email: String): Korisnik?
}
