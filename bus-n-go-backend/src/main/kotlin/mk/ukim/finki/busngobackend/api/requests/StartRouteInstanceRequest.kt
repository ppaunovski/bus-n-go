package mk.ukim.finki.busngobackend.api.requests

data class StartRouteInstanceRequest(
    val lineId: Int,
    val directionId: Long,
    val busId: Long,
)
