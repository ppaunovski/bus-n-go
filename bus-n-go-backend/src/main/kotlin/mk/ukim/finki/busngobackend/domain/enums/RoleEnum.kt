package mk.ukim.finki.busngobackend.domain.enums

enum class RoleEnum {
    ROLE_ADMIN,
    ROLE_DRIVER,
    ROLE_CONDUCTOR,
    ROLE_PASSENGER,
}

fun RoleEnum.toId(): Long = this.ordinal + 1L
