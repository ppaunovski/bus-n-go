package mk.ukim.finki.busngobackend.config

import mk.ukim.finki.busngobackend.encoder
import mk.ukim.finki.busngobackend.repository.KorisnikRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.core.userdetails.UserDetailsService

@Configuration
class ApplicationConfig(
    private val korisnikRepository: KorisnikRepository,
) {
    @Bean
    fun userDetailsService(): UserDetailsService =
        UserDetailsService { email ->
            korisnikRepository.findByEmail(email)
        }

    @Bean
    fun authenticationProvider(korisnikRepository: KorisnikRepository): AuthenticationProvider {
        val provider = DaoAuthenticationProvider()
        provider.setUserDetailsService(userDetailsService())
        provider.setPasswordEncoder(encoder())
        return provider
    }

    @Bean
    fun authenticationManager(config: AuthenticationConfiguration): AuthenticationManager = config.authenticationManager
}
