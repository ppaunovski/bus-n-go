package mk.ukim.finki.busngobackend.api

import mk.ukim.finki.busngobackend.domain.entities.Tipbilet
import mk.ukim.finki.busngobackend.repository.TipbiletRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/ticket-types")
class TicketTypeController(
    private val tipbiletRepository: TipbiletRepository,
) {
    @GetMapping()
    fun getTicketTypes(): List<Tipbilet> = tipbiletRepository.findAll()
}
