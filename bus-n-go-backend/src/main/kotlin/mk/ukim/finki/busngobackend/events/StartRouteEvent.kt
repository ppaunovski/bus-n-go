package mk.ukim.finki.busngobackend.events

import mk.ukim.finki.busngobackend.domain.entities.InstancaNaLinija
import org.springframework.context.ApplicationEvent

class StartRouteEvent(
    source: InstancaNaLinija,
) : ApplicationEvent(source)
