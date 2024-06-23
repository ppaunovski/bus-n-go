package mk.ukim.finki.busngobackend.domain.entities

import jakarta.persistence.*

@Entity
data class Patnik(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    @OneToOne(fetch = FetchType.EAGER)
    var korisnik: Korisnik,
)
