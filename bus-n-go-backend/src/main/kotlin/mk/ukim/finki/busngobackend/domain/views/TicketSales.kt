package mk.ukim.finki.busngobackend.domain.views

import jakarta.persistence.*
import mk.ukim.finki.busngobackend.domain.entities.Tipbilet

@Entity
@Table(name = "ticket_sales")
data class TicketSales(
    @Id
    val id: Long,
    @ManyToOne
    @JoinColumn(name = "tip_id")
    val type: Tipbilet,
    @Column(name = "interval_1h")
    val interval: String,
    @Column(name = "broj_pati")
    val numberOfSales: Long,
)
