package mk.ukim.finki.busngobackend.api.requests

data class AuthRequest(
    val email: String,
    val password: String,
)
