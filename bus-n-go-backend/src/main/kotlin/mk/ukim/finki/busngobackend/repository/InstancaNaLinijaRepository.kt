package mk.ukim.finki.busngobackend.repository

import mk.ukim.finki.busngobackend.domain.entities.InstancaNaLinija
import org.springframework.data.jpa.repository.JpaRepository

interface InstancaNaLinijaRepository : JpaRepository<InstancaNaLinija, Long>
