package mk.ukim.finki.busngobackend.domain.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.sql.Timestamp

@Entity
data class InstancaNaLinijaPostojkaNaLinija(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    @ManyToOne
    var postojkaNaLinija: PostojkaNaLinija,
    @ManyToOne
    var instancaNaLinija: InstancaNaLinija,
    var timestamp: Timestamp,
)
