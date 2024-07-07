package mk.ukim.finki.busngobackend.api.responses

import mk.ukim.finki.busngobackend.domain.entities.Tipbilet
import java.time.LocalDateTime

data class TicketResponse(
    var id: Long,
    var datumKupuvanje: LocalDateTime,
    var datumAktivacija: LocalDateTime?,
    var status: String,
    var tip: Tipbilet,
)
