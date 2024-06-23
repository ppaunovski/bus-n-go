package mk.ukim.finki.busngobackend.domain.entities

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
class Korisnik(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    val ime: String,
    val adresa: String,
    val telefon: String,
    var email: String,
    @Column(name = "is_admin")
    var admin: Boolean,
    var lozinka: String,
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "korisnik")
    var roles: List<KorisnikRole>,
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = roles.map { it.role }.toMutableList()

    override fun getPassword(): String = lozinka

    override fun getUsername(): String = email

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}
