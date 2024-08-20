package mk.ukim.finki.busngobackend.api

import mk.ukim.finki.busngobackend.api.requests.FineRequest
import mk.ukim.finki.busngobackend.service.FineService
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/fines")
class FineController(
    private val fineService: FineService,
) {
    @PutMapping()
    fun createFine(
        @RequestBody request: FineRequest,
    ) = fineService.createFine(request)
}
