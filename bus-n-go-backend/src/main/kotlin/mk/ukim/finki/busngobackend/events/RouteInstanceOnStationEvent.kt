package mk.ukim.finki.busngobackend.events

import mk.ukim.finki.busngobackend.domain.helpers.RouteInstanceOnStation
import org.springframework.context.ApplicationEvent

class RouteInstanceOnStationEvent(
    source: RouteInstanceOnStation,
) : ApplicationEvent(source)
