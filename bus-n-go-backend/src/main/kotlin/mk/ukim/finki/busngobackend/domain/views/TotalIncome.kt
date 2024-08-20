package mk.ukim.finki.busngobackend.domain.views

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "total_income")
class TotalIncome(
    @Id
    val id: Long,
    @Column(name = "year")
    val year: String,
    @Column(name = "fines_income")
    val finesIncome: Double,
    @Column(name = "ticket_income")
    val ticketIncome: Double,
    @Column(name = "total_income")
    val totalIncome: Double,
)
