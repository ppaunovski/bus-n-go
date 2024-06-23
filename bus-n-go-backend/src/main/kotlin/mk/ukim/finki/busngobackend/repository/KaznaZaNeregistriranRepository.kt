package mk.ukim.finki.busngobackend.repository

import mk.ukim.finki.busngobackend.domain.entities.KaznaZaNeregistriran
import org.springframework.data.jpa.repository.JpaRepository

interface KaznaZaNeregistriranRepository : JpaRepository<KaznaZaNeregistriran, Long>
