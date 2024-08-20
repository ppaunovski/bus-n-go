package mk.ukim.finki.busngobackend.service

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MaterializedViewService(
    private val jdbcTemplate: JdbcTemplate,
) {
    @Transactional
    fun refreshAverageTimeDiffs() {
        val sql = "REFRESH MATERIALIZED VIEW CONCURRENTLY avg_time_diffs;"
        jdbcTemplate.execute(sql)
    }

    @Transactional
    fun refreshCommutesByHour() {
        val sql = "refresh materialized view concurrently most_busy_part_of_the_day;"
        jdbcTemplate.execute(sql)
    }

    @Transactional
    fun refreshFinesPerLine() {
        val sql = "refresh materialized view concurrently kazna_po_linija;"
        jdbcTemplate.execute(sql)
    }
}
