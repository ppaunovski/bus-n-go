package mk.ukim.finki.busngobackend.simulator

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import mk.ukim.finki.busngobackend.domain.entities.InstancaNaLinija
import mk.ukim.finki.busngobackend.domain.entities.PostojkaNaLinija
import mk.ukim.finki.busngobackend.domain.helpers.RouteInstanceOnStation
import mk.ukim.finki.busngobackend.events.RouteInstanceOnStationEvent
import mk.ukim.finki.busngobackend.events.StopRouteInstanceEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.sql.Timestamp
import java.time.LocalDateTime

@Component
class RouteInstanceSimulator(
    private val eventPublisher: ApplicationEventPublisher,
) {
    @Async
    fun simulate(
        instance: InstancaNaLinija,
        stations: List<PostojkaNaLinija>,
    ) {
        runBlocking {
            stations.forEach { station ->
                delay(1000 * 20)
                eventPublisher.publishEvent(
                    RouteInstanceOnStationEvent(
                        RouteInstanceOnStation(
                            instance,
                            station,
                            Timestamp.valueOf(
                                LocalDateTime.now(),
                            ),
                        ),
                    ),
                )
            }
        }
        eventPublisher.publishEvent(StopRouteInstanceEvent(instance))
    }
}
