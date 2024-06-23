package mk.ukim.finki.busngobackend.api.requests

data class RegisterRequest(
    val email: String,
    val password: String,
    val confirmPassword: String,
    val name: String,
    val address: String,
    val phoneNumber: String,
)
