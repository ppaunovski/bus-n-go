package mk.ukim.finki.busngobackend.repository

import mk.ukim.finki.busngobackend.domain.entities.Avtobus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface AvtobusRepository : JpaRepository<Avtobus, Long> {
    @Query(
        nativeQuery = true,
        value =
            "select a.id, a.registracija, a.seriski_broj, a.broj_sedishta " +
                "from avtobus a " +
                "where not exists( " +
                "select 1 " +
                "from avtobus a1 " +
                "join instanca_na_linija inl on inl.avtobus_id = a1.id " +
                "where a1.id = a.id and " +
                "inl.end_date is null)",
    )
    fun findAllFreeBuses(): List<Avtobus>
}
