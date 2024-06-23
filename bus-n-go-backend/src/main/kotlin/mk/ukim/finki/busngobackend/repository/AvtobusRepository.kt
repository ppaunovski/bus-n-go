package mk.ukim.finki.busngobackend.repository

import mk.ukim.finki.busngobackend.domain.entities.Avtobus
import org.springframework.data.jpa.repository.JpaRepository

interface AvtobusRepository : JpaRepository<Avtobus, Long>
