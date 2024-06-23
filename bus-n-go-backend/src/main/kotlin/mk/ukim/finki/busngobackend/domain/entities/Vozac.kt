package mk.ukim.finki.busngobackend.domain.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToOne

@Entity
data class Vozac(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    @OneToOne
    var vraboten: Vraboten,
)
