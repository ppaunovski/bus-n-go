package mk.ukim.finki.busngobackend.domain.entities

import jakarta.persistence.*
import mk.ukim.finki.busngobackend.domain.enums.VozenjeStatus
import java.sql.Timestamp

@Entity
data class Vozenje(
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id
    var id: Long,
    var startDate: Timestamp,
    var endDate: Timestamp?,
    @Enumerated(EnumType.STRING)
    var status: VozenjeStatus,
    @ManyToOne
    var patnik: Patnik,
    @ManyToOne
    var postojkaNaLinijaStart: PostojkaNaLinija,
    @ManyToOne
    var instancaNaLinija: InstancaNaLinija,
    @ManyToOne
    var bilet: Bilet,
)
