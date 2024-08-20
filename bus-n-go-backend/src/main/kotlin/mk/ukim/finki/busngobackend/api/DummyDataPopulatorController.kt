package mk.ukim.finki.busngobackend.api

import mk.ukim.finki.busngobackend.simulator.DummyDataPopulator
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/dummy")
class DummyDataPopulatorController(
    private val dummyDataPopulator: DummyDataPopulator,
) {
    @GetMapping("/route-instances")
    fun populateRouteInstances() = dummyDataPopulator.populateRouteInstances()

    @GetMapping("/commutes")
    fun populateCommutes() = dummyDataPopulator.populateCommutes()
}
