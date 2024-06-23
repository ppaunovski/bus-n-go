package mk.ukim.finki.busngobackend.repository

import mk.ukim.finki.busngobackend.domain.entities.Kontrola
import org.springframework.data.jpa.repository.JpaRepository

interface KontrolaRepository : JpaRepository<Kontrola, Long>
