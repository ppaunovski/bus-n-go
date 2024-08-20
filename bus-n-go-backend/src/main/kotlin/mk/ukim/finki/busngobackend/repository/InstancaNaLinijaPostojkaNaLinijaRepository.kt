package mk.ukim.finki.busngobackend.repository

import mk.ukim.finki.busngobackend.domain.entities.InstancaNaLinijaPostojkaNaLinija
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.sql.Timestamp

interface InstancaNaLinijaPostojkaNaLinijaRepository : JpaRepository<InstancaNaLinijaPostojkaNaLinija, Long> {
    @Query(
        nativeQuery = true,
        value = """
            select id, instanca_na_linija_id, postojka_na_linija_id, timestamp from instanca_na_linija_postojka_na_linija where id = (
            select id from instanca_na_linija_postojka_na_linija where instanca_na_linija_id = ?1 order by EXTRACT(epoch FROM (timestamp - ?2)) limit 1
            )
        """,
    )
    fun findByInstancaNaLinijaAndTimestampNearestTo(
        instancaNaLinija: Long,
        startDate: Timestamp,
    ): InstancaNaLinijaPostojkaNaLinija?
}
