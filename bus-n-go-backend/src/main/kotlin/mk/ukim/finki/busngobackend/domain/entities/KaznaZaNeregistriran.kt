package mk.ukim.finki.busngobackend.domain.entities

import jakarta.persistence.*

@Entity
data class KaznaZaNeregistriran(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    @OneToOne
    val kazna: Kazna,
    var telefon: String,
    var ime: String,
    var adresa: String,
)
