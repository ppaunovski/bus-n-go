package mk.ukim.finki.busngobackend.api.responses

import mk.ukim.finki.busngobackend.domain.entities.Avtobus
import mk.ukim.finki.busngobackend.domain.entities.Linija
import mk.ukim.finki.busngobackend.domain.entities.Pravec
import java.time.LocalDateTime

data class RouteInstanceResponse(
    val id: Long,
    val bus: Avtobus,
    val direction: Pravec,
    val line: Linija,
    val driver: UserResponse,
    val start: LocalDateTime,
    val end: LocalDateTime?,
)
