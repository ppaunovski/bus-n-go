package mk.ukim.finki.busngobackend.api

import mk.ukim.finki.busngobackend.service.BusService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/buses")
class BusController(
    private val busService: BusService,
) {
    @GetMapping("")
    fun getAllBuses() = busService.findAll()

    @GetMapping("/free")
    fun getAllFrees() = busService.findAllFree()
}
