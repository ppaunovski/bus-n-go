package mk.ukim.finki.busngobackend.domain.entities

import jakarta.persistence.*

@Entity
data class Kondukter(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    @OneToOne
    var vraboten: Vraboten,
)
