package mk.ukim.finki.busngobackend.api

import mk.ukim.finki.busngobackend.service.AdminService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/admin")
class AdminController(
    private val adminService: AdminService,
) {
    @GetMapping("/average-time-between-stations")
    fun getAvgTimeBetweenStations() = adminService.getAverageTimeBetweenStations()

    @GetMapping("/fines-by-line")
    fun getFinesByLine() = adminService.getFinesByLine()

    @GetMapping("/commutes-by-hour")
    fun getCommutesByHour() = adminService.getCommutesByHour()

    @GetMapping("/commutes-by-line")
    fun getCommutesByLine() = adminService.getCommutesByLine()

    @GetMapping("/number-of-passengers-per-line-and-station")
    fun getNumberOfPassengersPerLineAndStation() = adminService.getNumberOfPassengersByLineAndStation()

    @GetMapping("/ticket-sales")
    fun getTicketSales() = adminService.getTicketSales()

    @GetMapping("/total-income")
    fun getTotalIncome() = adminService.getTotalIncome()

    @GetMapping("/all-users")
    fun getAllUsers() = adminService.getAllUsers()

    @PostMapping("/update-roles/{id}")
    fun updateRolesForUser(
        @PathVariable id: Long,
        @RequestBody roles: List<String>,
    ) = adminService.updateRolesForUser(id, roles)
}
