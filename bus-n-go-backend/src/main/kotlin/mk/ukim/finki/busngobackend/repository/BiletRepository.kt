package mk.ukim.finki.busngobackend.repository

import mk.ukim.finki.busngobackend.domain.entities.Bilet
import org.springframework.data.jpa.repository.JpaRepository

interface BiletRepository : JpaRepository<Bilet, Long>
