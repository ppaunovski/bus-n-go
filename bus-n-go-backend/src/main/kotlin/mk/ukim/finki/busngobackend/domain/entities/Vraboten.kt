package mk.ukim.finki.busngobackend.domain.entities

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.sql.Date

@Entity
data class Vraboten(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    @jakarta.persistence.OneToOne(fetch = FetchType.EAGER)
    var korisnik: Korisnik,
    var plata: Double,
    val datumNaVrabotuvanje: Date,
    var datumPrekinVrabotuvanje: Date?,
)
