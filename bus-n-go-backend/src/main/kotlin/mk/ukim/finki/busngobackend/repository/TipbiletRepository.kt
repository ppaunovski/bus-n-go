package mk.ukim.finki.busngobackend.repository

import mk.ukim.finki.busngobackend.domain.entities.Tipbilet
import org.springframework.data.jpa.repository.JpaRepository

interface TipbiletRepository : JpaRepository<Tipbilet, Long>
