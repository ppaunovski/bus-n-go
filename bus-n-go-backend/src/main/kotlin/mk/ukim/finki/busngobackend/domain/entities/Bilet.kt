package mk.ukim.finki.busngobackend.domain.entities

import jakarta.persistence.*
import mk.ukim.finki.busngobackend.domain.enums.BiletEnum
import java.sql.Timestamp

@Entity
data class Bilet(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    var datumKupuvanje: Timestamp,
    var datumAktivacija: Timestamp?,
    @Enumerated(EnumType.STRING)
    var status: BiletEnum,
    @ManyToOne
    var patnik: Patnik,
    @ManyToOne
    var tip: Tipbilet,
)
