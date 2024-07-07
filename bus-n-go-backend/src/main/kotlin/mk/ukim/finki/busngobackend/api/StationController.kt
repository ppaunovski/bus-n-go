package mk.ukim.finki.busngobackend.api

import mk.ukim.finki.busngobackend.service.StationService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/stations")
class StationController(
    private val stationService: StationService,
)
