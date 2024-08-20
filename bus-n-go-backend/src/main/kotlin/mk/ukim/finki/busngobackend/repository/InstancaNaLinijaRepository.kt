package mk.ukim.finki.busngobackend.repository

import mk.ukim.finki.busngobackend.domain.entities.InstancaNaLinija
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface InstancaNaLinijaRepository : JpaRepository<InstancaNaLinija, Long> {
    @Query(
        value = "select count(id) > 0 from instanca_na_linija inl where inl.vozac_id=:vozac and inl.end_date is null",
        nativeQuery = true,
    )
    fun existsByVozacAndEndDateIsNull(vozac: Long): Boolean

    @Query(
        value =
            """
            select distinct inl.id
from instanca_na_linija inl
         join instanca_na_linija_postojka_na_linija inlpnl on inl.id = inlpnl.instanca_na_linija_id
where end_date is null
  and linija_id in (
    select distinct(pnl.linija_id)
    from postojka_na_linija pnl
    where postojka_id = ?1
)
except
select inl.id
from postojka_na_linija pnl
         join instanca_na_linija inl on inl.linija_id = pnl.linija_id and inl.pravec_id = pnl.pravec_id
join instanca_na_linija_postojka_na_linija inlpnl on inl.id = inlpnl.instanca_na_linija_id and pnl.id = inlpnl.postojka_na_linija_id
where inl.end_date is null and postojka_id = ?1;
        """,
        nativeQuery = true,
    )
    fun findIncomingRouteInstancesForStation(stationId: Int): List<Long>

    fun findAllByEndDateIsNull(): List<InstancaNaLinija>
}
