package mk.ukim.finki.busngobackend.api.responses

import mk.ukim.finki.busngobackend.domain.entities.PostojkaNaLinija
import java.time.LocalDateTime

data class CommuteResponse(
    val commuter: UserResponse,
    val ticket: TicketResponse,
    val stationStart: PostojkaNaLinija,
    val status: String,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime?,
    val id: Long,
)
