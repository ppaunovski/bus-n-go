package mk.ukim.finki.busngobackend.repository

import mk.ukim.finki.busngobackend.domain.entities.Vozenje
import org.springframework.data.jpa.repository.JpaRepository

interface VozenjeRepository : JpaRepository<Vozenje, Long>
