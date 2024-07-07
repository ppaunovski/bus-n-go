package mk.ukim.finki.busngobackend.repository

import mk.ukim.finki.busngobackend.domain.entities.Linija
import mk.ukim.finki.busngobackend.domain.entities.Postojka
import mk.ukim.finki.busngobackend.domain.entities.PostojkaNaLinija
import mk.ukim.finki.busngobackend.domain.entities.Pravec
import org.springframework.data.jpa.repository.JpaRepository

interface PostojkaNaLinijaRepository : JpaRepository<PostojkaNaLinija, Long> {
    fun findByLinija(linija: Linija): List<PostojkaNaLinija>

    fun findByLinijaAndPravec(
        linija: Linija,
        pravec: Pravec,
    ): List<PostojkaNaLinija>

    fun existsByLinijaAndPostojka(
        linija: Linija,
        postojkaStart: Postojka,
    ): Boolean

    fun findByLinijaAndPostojka(
        linija: Linija,
        postojkaStart: Postojka,
    ): PostojkaNaLinija?
}
