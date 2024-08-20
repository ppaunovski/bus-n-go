package mk.ukim.finki.busngobackend.api.responses

import java.sql.Timestamp

data class ControlResponse(
    var id: Long,
    val dateCreated: Timestamp,
    val kondukter: UserResponse,
    val instancaNaLinija: RouteInstanceResponse,
)
