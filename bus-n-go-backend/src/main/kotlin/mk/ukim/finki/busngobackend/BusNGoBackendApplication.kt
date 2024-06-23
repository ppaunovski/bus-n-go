package mk.ukim.finki.busngobackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.password.PasswordEncoder

@SpringBootApplication
class BusNGoBackendApplication

fun main(args: Array<String>) {
    runApplication<BusNGoBackendApplication>(*args)
}

@Bean
fun encoder(): PasswordEncoder =
    object : PasswordEncoder {
        override fun encode(rawPassword: CharSequence?): String = rawPassword.toString()

        override fun matches(
            rawPassword: CharSequence?,
            encodedPassword: String?,
        ): Boolean = rawPassword != null && rawPassword.toString() == encodedPassword
    }
