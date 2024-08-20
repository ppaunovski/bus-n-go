package mk.ukim.finki.busngobackend.api.responses

data class AdminUsersResponse(
    val email: String,
    val name: String,
    val address: String,
    val phoneNumber: String,
    val id: Long,
    val roles: List<String>,
)
