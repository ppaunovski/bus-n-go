package mk.ukim.finki.busngobackend.domain.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Postojka(
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id
    var id: Int,
    var ime: String,
    var lat: Double,
    var lon: Double,
    var opis: String,
)
