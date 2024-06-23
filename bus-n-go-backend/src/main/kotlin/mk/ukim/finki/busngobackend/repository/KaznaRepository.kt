package mk.ukim.finki.busngobackend.repository

import mk.ukim.finki.busngobackend.domain.entities.Kazna
import org.springframework.data.jpa.repository.JpaRepository

interface KaznaRepository : JpaRepository<Kazna, Long>
