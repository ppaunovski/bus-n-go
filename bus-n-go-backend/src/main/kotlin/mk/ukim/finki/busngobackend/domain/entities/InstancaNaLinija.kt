package mk.ukim.finki.busngobackend.domain.entities

import jakarta.persistence.*
import java.sql.Timestamp

@Entity
data class InstancaNaLinija(
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id
    var id: Long,
    var startDate: Timestamp,
    var endDate: Timestamp?,
    @ManyToOne
    var vozac: Vozac,
    @ManyToOne
    var avtobus: Avtobus,
    @ManyToOne
    var linija: Linija,
)
