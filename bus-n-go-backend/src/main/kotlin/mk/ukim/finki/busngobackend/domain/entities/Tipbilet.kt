package mk.ukim.finki.busngobackend.domain.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Tipbilet(
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id
    var id: Long,
    var trajnost: Long,
    var cena: Float,
    var ime: String,
)
