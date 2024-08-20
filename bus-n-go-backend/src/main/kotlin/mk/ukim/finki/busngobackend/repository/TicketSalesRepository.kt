package mk.ukim.finki.busngobackend.repository

import mk.ukim.finki.busngobackend.domain.views.TicketSales
import org.springframework.data.jpa.repository.JpaRepository

interface TicketSalesRepository : JpaRepository<TicketSales, Long>
