package mk.ukim.finki.busngobackend.repository

import mk.ukim.finki.busngobackend.domain.views.NumberOfPassengersByLineAndStation
import org.springframework.data.jpa.repository.JpaRepository

interface NumberOfPassengersByLineAndStationRepository : JpaRepository<NumberOfPassengersByLineAndStation, Long>
