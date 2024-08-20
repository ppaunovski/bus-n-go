package mk.ukim.finki.busngobackend.api.responses

data class UserResponse(
    val email: String,
    val name: String,
    val address: String,
    val phoneNumber: String,
    val id: Long,
)
