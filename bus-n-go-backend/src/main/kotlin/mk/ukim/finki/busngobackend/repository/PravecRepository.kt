package mk.ukim.finki.busngobackend.repository

import mk.ukim.finki.busngobackend.domain.entities.Pravec
import org.springframework.data.jpa.repository.JpaRepository

interface PravecRepository : JpaRepository<Pravec, Long>
