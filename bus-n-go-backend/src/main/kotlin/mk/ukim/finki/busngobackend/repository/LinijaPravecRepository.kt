package mk.ukim.finki.busngobackend.repository

import mk.ukim.finki.busngobackend.domain.entities.LinijaPravec
import org.springframework.data.jpa.repository.JpaRepository

interface LinijaPravecRepository : JpaRepository<LinijaPravec, Long>
