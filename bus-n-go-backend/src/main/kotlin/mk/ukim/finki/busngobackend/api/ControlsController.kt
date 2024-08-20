package mk.ukim.finki.busngobackend.api

import mk.ukim.finki.busngobackend.service.ControlService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/controls")
class ControlsController(
    private val controlService: ControlService,
) {
    @GetMapping()
    fun getControls() = controlService.getAllByConductor()

    @PutMapping("/start")
    fun start(
        @RequestBody routeInstanceId: Long,
    ) = controlService.start(routeInstanceId)

    @GetMapping("/{controlId}/fines")
    fun getFines(
        @PathVariable controlId: Long,
    ) = controlService.getFines(controlId)
}
