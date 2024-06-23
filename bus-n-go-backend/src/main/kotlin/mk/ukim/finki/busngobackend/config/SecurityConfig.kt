package mk.ukim.finki.busngobackend.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtAuthFilter: JwtAuthFilter,
    private val authenticationProvider: AuthenticationProvider,
) {
    @Bean
    fun securityFilterChain(
        http: HttpSecurity,
        jwtAuthFilter: JwtAuthFilter,
    ): DefaultSecurityFilterChain =
        http
            .csrf {
                it.disable()
            }.authorizeHttpRequests {
                it
                    .requestMatchers("/api/booking/populate")
                    .permitAll()
                    .requestMatchers("/api/booking/**")
                    .authenticated()
                    .requestMatchers(HttpMethod.GET, "/api/city/**")
                    .permitAll()
                    .requestMatchers("/api/city/**")
                    .authenticated()
                    .requestMatchers(HttpMethod.POST, "/api/properties/**")
                    .authenticated()
                    .requestMatchers(HttpMethod.GET, "/api/properties/{id}/for-review")
                    .authenticated()
                    .requestMatchers(HttpMethod.POST, "/api/property-image/{id}/save-file")
                    .permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/property-image/**")
                    .authenticated()
                    .requestMatchers(HttpMethod.POST, "/api/reviews/**")
                    .authenticated()
                    .anyRequest()
                    .permitAll()
            }.sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }.authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()
}
