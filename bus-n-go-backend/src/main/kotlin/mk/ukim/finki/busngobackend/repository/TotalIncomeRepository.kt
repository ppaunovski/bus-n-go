package mk.ukim.finki.busngobackend.repository

import mk.ukim.finki.busngobackend.domain.views.TotalIncome
import org.springframework.data.jpa.repository.JpaRepository

interface TotalIncomeRepository : JpaRepository<TotalIncome, Long>
