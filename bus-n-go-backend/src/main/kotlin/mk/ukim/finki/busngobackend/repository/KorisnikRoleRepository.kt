package mk.ukim.finki.busngobackend.repository

import mk.ukim.finki.busngobackend.domain.entities.KorisnikRole
import org.springframework.data.jpa.repository.JpaRepository

interface KorisnikRoleRepository : JpaRepository<KorisnikRole, Long>
