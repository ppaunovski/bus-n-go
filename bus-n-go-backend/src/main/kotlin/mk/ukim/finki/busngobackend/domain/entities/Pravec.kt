package mk.ukim.finki.busngobackend.domain.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Pravec(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    var pravec: String,
    var opis: String,
)
