package mk.ukim.finki.busngobackend.api

import mk.ukim.finki.busngobackend.api.requests.StartCommuteRequest
import mk.ukim.finki.busngobackend.service.CommuteService
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/commutes")
class CommuteController(
    private val commuteService: CommuteService,
) {
    @PutMapping("/start")
    fun startCommute(
        @RequestBody request: StartCommuteRequest,
    ) = commuteService.start(request)

    // subscribe to the topic for that station
    // receive the incoming route instances
    // choose one as target and start commute
}
