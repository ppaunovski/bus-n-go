package mk.ukim.finki.busngobackend.api

import mk.ukim.finki.busngobackend.api.requests.TicketBuyRequest
import mk.ukim.finki.busngobackend.api.responses.TicketResponse
import mk.ukim.finki.busngobackend.service.BiletService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/tickets")
class TicketController(
    private val biletService: BiletService,
) {
    @GetMapping()
    fun getTicketsByUser(): List<TicketResponse> = this.biletService.getTicketsByUser()

    @PostMapping("/buy")
    fun buyTicket(
        @RequestBody request: TicketBuyRequest,
    ): TicketResponse = this.biletService.buyTicket(request)

    @PatchMapping("/activate/{id}")
    fun activateTicket(
        @PathVariable id: Long,
    ): TicketResponse = this.biletService.activateTicket(id)
}
