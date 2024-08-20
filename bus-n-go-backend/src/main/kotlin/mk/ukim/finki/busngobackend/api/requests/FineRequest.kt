package mk.ukim.finki.busngobackend.api.requests

data class FineRequest(
    var iznos: Double,
    var plateno: Boolean,
    var dokument: String,
    val kontrolaId: Long,
    var telefon: String?,
    var ime: String?,
    var adresa: String?,
    var patnikId: Long?,
)
