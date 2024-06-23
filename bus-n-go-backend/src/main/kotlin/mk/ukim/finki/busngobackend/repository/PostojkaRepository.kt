package mk.ukim.finki.busngobackend.repository

import mk.ukim.finki.busngobackend.domain.entities.Postojka
import org.springframework.data.jpa.repository.JpaRepository

interface PostojkaRepository : JpaRepository<Postojka, Long>
