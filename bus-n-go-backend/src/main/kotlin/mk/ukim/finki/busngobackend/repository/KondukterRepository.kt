package mk.ukim.finki.busngobackend.repository

import mk.ukim.finki.busngobackend.domain.entities.Kondukter
import org.springframework.data.jpa.repository.JpaRepository

interface KondukterRepository : JpaRepository<Kondukter, Long>
