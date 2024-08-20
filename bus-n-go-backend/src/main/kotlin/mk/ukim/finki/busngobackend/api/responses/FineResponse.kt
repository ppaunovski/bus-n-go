package mk.ukim.finki.busngobackend.api.responses

import java.sql.Timestamp

data class FineResponse(
    var id: Long,
    var iznos: Double,
    var plateno: Boolean,
    val dateCreated: Timestamp,
    var datePayed: Timestamp?,
    var dokument: String,
    val kondukter: UserResponse,
    val kontrola: ControlResponse,
    val patnik: UserResponse?,
    var telefon: String?,
    var ime: String?,
    var adresa: String?,
)
