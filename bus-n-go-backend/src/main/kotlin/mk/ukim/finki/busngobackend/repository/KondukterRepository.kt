package mk.ukim.finki.busngobackend.repository

import mk.ukim.finki.busngobackend.domain.entities.Kondukter
import mk.ukim.finki.busngobackend.domain.entities.Vraboten
import org.springframework.data.jpa.repository.JpaRepository

interface KondukterRepository : JpaRepository<Kondukter, Long> {
    fun findByVraboten(vraboten: Vraboten): Kondukter?
}
