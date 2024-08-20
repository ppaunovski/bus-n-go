package mk.ukim.finki.busngobackend.domain.views

import jakarta.persistence.*
import mk.ukim.finki.busngobackend.domain.entities.Linija
import mk.ukim.finki.busngobackend.domain.entities.Postojka

@Entity
@Table(name = "number_passengers_per_line_and_station")
data class NumberOfPassengersByLineAndStation(
    @Id
    val id: Long,
    @ManyToOne
    @JoinColumn(name = "line_id")
    val line: Linija,
    @ManyToOne
    @JoinColumn(name = "station_id")
    val station: Postojka,
    @Column(name = "count")
    val passengersByLineAndStation: Long,
)
