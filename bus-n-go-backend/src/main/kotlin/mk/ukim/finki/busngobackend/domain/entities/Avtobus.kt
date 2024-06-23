package mk.ukim.finki.busngobackend.domain.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Avtobus(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    var registracija: String,
    var seriskiBroj: String,
    var brojSedishta: Short?,
)
