package mk.ukim.finki.busngobackend.repository

import mk.ukim.finki.busngobackend.domain.entities.InstancaNaLinija
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface InstancaNaLinijaRepository : JpaRepository<InstancaNaLinija, Long> {
    @Query(
        value = "select count(id) > 0 from instanca_na_linija inl where inl.vozac_id=:vozac and inl.end_date is not null",
        nativeQuery = true,
    )
    fun existsByVozacAndEndDateIsNull(vozac: Long): Boolean
}
