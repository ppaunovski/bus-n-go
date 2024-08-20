package mk.ukim.finki.busngobackend.repository

import mk.ukim.finki.busngobackend.domain.views.FinesByLine
import org.springframework.data.jpa.repository.JpaRepository

interface FinesByLineRepository : JpaRepository<FinesByLine, Long>
