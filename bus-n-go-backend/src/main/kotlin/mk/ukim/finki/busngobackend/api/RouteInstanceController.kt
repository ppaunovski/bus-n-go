package mk.ukim.finki.busngobackend.api

import mk.ukim.finki.busngobackend.api.requests.StartRouteInstanceRequest
import mk.ukim.finki.busngobackend.service.RouteInstanceService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/route-instances")
class RouteInstanceController(
    private val routeInstanceService: RouteInstanceService,
) {
    @PostMapping("/start")
    fun start(
        @RequestBody request: StartRouteInstanceRequest,
    ) = routeInstanceService.start(request)

    @PatchMapping("/stop")
    fun stop(
        @RequestBody id: Long,
    ) = routeInstanceService.stop(id)

    @GetMapping("/{id}")
    fun getById(
        @PathVariable id: Long,
    ) = this.routeInstanceService.getById(id)
}
