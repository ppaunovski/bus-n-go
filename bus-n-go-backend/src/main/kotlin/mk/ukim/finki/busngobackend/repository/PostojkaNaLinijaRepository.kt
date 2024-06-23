package mk.ukim.finki.busngobackend.repository

import mk.ukim.finki.busngobackend.domain.entities.PostojkaNaLinija
import org.springframework.data.jpa.repository.JpaRepository

interface PostojkaNaLinijaRepository : JpaRepository<PostojkaNaLinija, Long>
