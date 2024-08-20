package mk.ukim.finki.busngobackend.domain.views

import jakarta.persistence.*
import mk.ukim.finki.busngobackend.domain.entities.PostojkaNaLinija

@Entity
@Table(name = "avg_time_diffs")
data class AverageTimeBetweenStations(
    @Column(name = "id")
    @Id
    val id: Long,
    @ManyToOne
    @JoinColumn(name = "start_station_id")
    val startStation: PostojkaNaLinija,
    @ManyToOne
    @JoinColumn(name = "end_station_id")
    val endStation: PostojkaNaLinija,
    @Column(name = "avg_time_diff_seconds")
    val timeInSeconds: Double,
)
