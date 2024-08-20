package mk.ukim.finki.busngobackend.repository

import mk.ukim.finki.busngobackend.domain.views.CommutesByLine
import org.springframework.data.jpa.repository.JpaRepository

interface CommutesByLineRepository : JpaRepository<CommutesByLine, Long>
