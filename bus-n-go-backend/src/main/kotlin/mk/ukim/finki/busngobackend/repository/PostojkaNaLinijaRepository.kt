package mk.ukim.finki.busngobackend.repository

import mk.ukim.finki.busngobackend.domain.entities.Linija
import mk.ukim.finki.busngobackend.domain.entities.Postojka
import mk.ukim.finki.busngobackend.domain.entities.PostojkaNaLinija
import mk.ukim.finki.busngobackend.domain.entities.Pravec
import mk.ukim.finki.busngobackend.domain.views.AverageTimeBetweenStations
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface PostojkaNaLinijaRepository : JpaRepository<PostojkaNaLinija, Long> {
    fun findByLinija(linija: Linija): List<PostojkaNaLinija>

    fun findByLinijaAndPravec(
        linija: Linija,
        pravec: Pravec,
    ): List<PostojkaNaLinija>

    fun existsByLinijaAndPostojka(
        linija: Linija,
        postojkaStart: Postojka,
    ): Boolean

    fun findByLinijaAndPostojka(
        linija: Linija,
        postojkaStart: Postojka,
    ): PostojkaNaLinija?

    fun findByLinijaAndPostojkaAndPravec(
        linija: Linija,
        postojkaStart: Postojka,
        pravec: Pravec,
    ): PostojkaNaLinija?

    @Query(
        nativeQuery = true,
        value = """
            select id, start_station_id, end_station_id, avg_time_diff_seconds from avg_time_diffs
        """,
    )
    fun getAverageTimeBetweenStations(): List<AverageTimeBetweenStations>
}
