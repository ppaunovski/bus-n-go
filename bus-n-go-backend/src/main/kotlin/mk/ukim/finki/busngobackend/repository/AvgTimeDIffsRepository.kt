package mk.ukim.finki.busngobackend.repository

import mk.ukim.finki.busngobackend.domain.views.AverageTimeBetweenStations
import org.springframework.data.jpa.repository.JpaRepository

interface AvgTimeDIffsRepository : JpaRepository<AverageTimeBetweenStations, Long>
