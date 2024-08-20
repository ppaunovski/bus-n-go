package mk.ukim.finki.busngobackend.domain.helpers

import mk.ukim.finki.busngobackend.domain.entities.InstancaNaLinija
import mk.ukim.finki.busngobackend.domain.entities.PostojkaNaLinija
import java.sql.Timestamp

data class RouteInstanceOnStation(
    val instance: InstancaNaLinija,
    val station: PostojkaNaLinija,
    val timestamp: Timestamp,
)
