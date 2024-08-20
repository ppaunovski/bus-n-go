package mk.ukim.finki.busngobackend.domain.views

import jakarta.persistence.*
import mk.ukim.finki.busngobackend.domain.entities.Linija

@Entity
@Table(name = "most_busy_part_of_the_day")
data class CommutesByHour(
    @Id
    val id: Long,
    @ManyToOne
    @JoinColumn(name = "linija_id")
    val line: Linija,
    @Column(name = "interval_1h")
    val interval: String,
    @Column(name = "broj_pati")
    val numberOfCommutes: Long,
)
