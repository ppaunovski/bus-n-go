package mk.ukim.finki.busngobackend.domain.views

import jakarta.persistence.*
import mk.ukim.finki.busngobackend.domain.entities.Linija

@Entity
@Table(name = "kazna_po_linija")
data class FinesByLine(
    @Id
    val id: Long,
    @JoinColumn(name = "line_id")
    @ManyToOne
    val line: Linija,
    @Column(name = "count")
    val count: Long,
)
