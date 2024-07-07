package mk.ukim.finki.busngobackend.api

import mk.ukim.finki.busngobackend.api.requests.StartCommuteRequest
import mk.ukim.finki.busngobackend.service.CommuteService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/commutes")
class CommuteController(
    private val commuteService: CommuteService,
) {
    @PostMapping("/start")
    fun startCommute(
        @RequestBody request: StartCommuteRequest,
    ) = commuteService.start(request)
}
