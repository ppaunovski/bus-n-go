package mk.ukim.finki.busngobackend.api

import mk.ukim.finki.busngobackend.api.responses.UserResponse
import mk.ukim.finki.busngobackend.service.KorisnikService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserController(
    private val korisnikService: KorisnikService,
) {
    @GetMapping()
    fun getAuthenticatedUser(): UserResponse = korisnikService.getAuthenticatedUser()
}
