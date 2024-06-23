package mk.ukim.finki.busngobackend.repository

import mk.ukim.finki.busngobackend.domain.entities.Linija
import org.springframework.data.jpa.repository.JpaRepository

interface LinijaRepository : JpaRepository<Linija, Int>
