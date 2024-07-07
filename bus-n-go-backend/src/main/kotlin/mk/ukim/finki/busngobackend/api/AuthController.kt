package mk.ukim.finki.busngobackend.api

import mk.ukim.finki.busngobackend.api.requests.AuthRequest
import mk.ukim.finki.busngobackend.api.requests.RegisterRequest
import mk.ukim.finki.busngobackend.api.responses.AuthResponse
import mk.ukim.finki.busngobackend.service.AuthService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authService: AuthService,
) {
    @GetMapping()
    fun isAuthenticated() = authService.isAuthenticated()

    @PostMapping("/register")
    fun register(
        @RequestBody registerRequest: RegisterRequest,
    ): AuthResponse = authService.register(registerRequest)

    @PostMapping()
    fun authenticate(
        @RequestBody authRequest: AuthRequest,
    ): AuthResponse = authService.authenticate(authRequest)

    @GetMapping("/is-driver-free")
    fun isDriverFree() = this.authService.isDriverFree()
}
