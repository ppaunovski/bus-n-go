package mk.ukim.finki.busngobackend.domain.entities

import jakarta.persistence.*

@Entity
data class KaznaZaRegistriran(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    @OneToOne
    val kazna: Kazna,
    @ManyToOne
    val patnik: Patnik,
)
