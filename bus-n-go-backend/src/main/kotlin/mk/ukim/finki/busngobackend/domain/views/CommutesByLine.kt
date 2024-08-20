package mk.ukim.finki.busngobackend.domain.views

import jakarta.persistence.*
import mk.ukim.finki.busngobackend.domain.entities.Linija

@Entity
@Table(name = "commutes_by_line")
data class CommutesByLine(
    @Id
    val id: Long,
    @ManyToOne
    @JoinColumn(name = "linija_id")
    val line: Linija,
    @Column(name = "count")
    val commutesPerLine: Long,
)
