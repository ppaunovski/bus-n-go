package mk.ukim.finki.busngobackend.repository

import mk.ukim.finki.busngobackend.domain.entities.KaznaZaRegistriran
import org.springframework.data.jpa.repository.JpaRepository

interface KaznaZaRegistriranRepository : JpaRepository<KaznaZaRegistriran, Long>
