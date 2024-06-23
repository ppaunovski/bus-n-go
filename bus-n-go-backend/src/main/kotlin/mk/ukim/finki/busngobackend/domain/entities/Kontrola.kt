package mk.ukim.finki.busngobackend.domain.entities

import jakarta.persistence.*
import java.sql.Timestamp

@Entity
data class Kontrola(
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id
    var id: Long,
    val dateCreated: Timestamp,
    @ManyToOne
    val kondukter: Kondukter,
    @ManyToOne
    val instancaNaLinija: InstancaNaLinija,
)
