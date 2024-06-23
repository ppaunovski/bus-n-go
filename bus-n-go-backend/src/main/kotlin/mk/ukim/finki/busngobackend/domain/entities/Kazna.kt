package mk.ukim.finki.busngobackend.domain.entities

import jakarta.persistence.*
import java.sql.Timestamp

@Entity
data class Kazna(
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id
    var id: Long,
    var iznos: Double,
    var plateno: Boolean,
    val dateCreated: Timestamp,
    var datePayed: Timestamp?,
    var dokument: String,
    @ManyToOne
    val kondukter: Kondukter,
    @ManyToOne
    val kontrola: Kontrola,
)
