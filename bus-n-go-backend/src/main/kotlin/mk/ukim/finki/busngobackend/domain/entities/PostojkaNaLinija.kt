package mk.ukim.finki.busngobackend.domain.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne

@Entity
data class PostojkaNaLinija(
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id
    var id: Long,
    var redenBroj: Short,
    @ManyToOne
    val linija: Linija,
    @ManyToOne
    val postojka: Postojka,
)
