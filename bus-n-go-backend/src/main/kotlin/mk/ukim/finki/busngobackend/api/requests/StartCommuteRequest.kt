package mk.ukim.finki.busngobackend.api.requests

data class StartCommuteRequest(
    val ticketId: Long,
    val routeInstanceId: Long,
    val lineStationId: Long,
)
