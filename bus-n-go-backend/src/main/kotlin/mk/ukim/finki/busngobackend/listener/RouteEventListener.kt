package mk.ukim.finki.busngobackend.listener

import mk.ukim.finki.busngobackend.domain.entities.InstancaNaLinija
import mk.ukim.finki.busngobackend.domain.helpers.RouteInstanceOnStation
import mk.ukim.finki.busngobackend.events.RouteInstanceOnStationEvent
import mk.ukim.finki.busngobackend.events.StartRouteEvent
import mk.ukim.finki.busngobackend.events.StopRouteInstanceEvent
import mk.ukim.finki.busngobackend.service.RouteInstanceOnStationService
import mk.ukim.finki.busngobackend.service.RouteInstanceService
import mk.ukim.finki.busngobackend.service.StationService
import mk.ukim.finki.busngobackend.service.WebSocketService
import mk.ukim.finki.busngobackend.simulator.RouteInstanceSimulator
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class RouteEventListener(
    private val stationService: StationService,
    private val simulator: RouteInstanceSimulator,
    private val eventPublisher: ApplicationEventPublisher,
    private val routeInstanceService: RouteInstanceService,
    private val routeInstanceOnStationService: RouteInstanceOnStationService,
    private val webSocketService: WebSocketService,
) {
    @Async
    @EventListener
    fun startRouteEvent(event: StartRouteEvent) {
        println(event)
        println(event)
        val instance = event.source as InstancaNaLinija
        val line = instance.linija
        val direction = instance.pravec
        val stations = stationService.findStationsByLineIdAndDirectionId(line.id, direction.id)

        simulator.simulate(instance, stations)
    }

    @Async
    @EventListener
    fun routeInstanceOnStation(event: RouteInstanceOnStationEvent) {
        println("------------------------------------------------")
        println(event)
        val source = event.source as RouteInstanceOnStation
        routeInstanceOnStationService.create(source.instance.id, source.station.id, source.timestamp)
        // todo: publish event to the websocket so that the user gets notified
        webSocketService.sendMessage("routes", "Updated routes")
    }

    @EventListener
    fun stopRouteEvent(event: StopRouteInstanceEvent) {
        val instance = event.source as InstancaNaLinija
        routeInstanceService.stop(instance.id)
    }
}
