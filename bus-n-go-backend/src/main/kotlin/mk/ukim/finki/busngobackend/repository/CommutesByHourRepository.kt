package mk.ukim.finki.busngobackend.repository

import mk.ukim.finki.busngobackend.domain.views.CommutesByHour
import org.springframework.data.jpa.repository.JpaRepository

interface CommutesByHourRepository : JpaRepository<CommutesByHour, Long>
