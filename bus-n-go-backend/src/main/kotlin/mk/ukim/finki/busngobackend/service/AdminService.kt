package mk.ukim.finki.busngobackend.service

import mk.ukim.finki.busngobackend.api.responses.AdminUsersResponse
import mk.ukim.finki.busngobackend.domain.entities.KorisnikRole
import mk.ukim.finki.busngobackend.domain.enums.RoleEnum
import mk.ukim.finki.busngobackend.domain.views.*
import mk.ukim.finki.busngobackend.repository.*
import mk.ukim.finki.busngobackend.service.exceptions.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class AdminService(
    private val avgTimeDIffsRepository: AvgTimeDIffsRepository,
    private val finesByLineRepository: FinesByLineRepository,
    private val commutesByHourRepository: CommutesByHourRepository,
    private val commutesByLineRepository: CommutesByLineRepository,
    private val numberOfPassengersByLineAndStationRepository: NumberOfPassengersByLineAndStationRepository,
    private val ticketSalesRepository: TicketSalesRepository,
    private val totalIncomeRepository: TotalIncomeRepository,
    private val korisnikService: KorisnikService,
    private val korisnikRepository: KorisnikRepository,
    private val roleRepository: RoleRepository,
    private val korisnikRoleRepository: KorisnikRoleRepository,
    private val authService: AuthService,
) {
    fun getAverageTimeBetweenStations(): List<AverageTimeBetweenStations> {
        authService.hasAuthority(RoleEnum.ROLE_ADMIN)
        return avgTimeDIffsRepository.findAll()
    }

    fun getFinesByLine(): List<FinesByLine> {
        authService.hasAuthority(RoleEnum.ROLE_ADMIN)
        return finesByLineRepository.findAll()
    }

    fun getCommutesByHour(): List<CommutesByHour> {
        authService.hasAuthority(RoleEnum.ROLE_ADMIN)
        return commutesByHourRepository.findAll()
    }

    fun getCommutesByLine(): List<CommutesByLine> {
        authService.hasAuthority(RoleEnum.ROLE_ADMIN)
        return commutesByLineRepository.findAll()
    }

    fun getNumberOfPassengersByLineAndStation(): List<NumberOfPassengersByLineAndStation> {
        authService.hasAuthority(RoleEnum.ROLE_ADMIN)
        return numberOfPassengersByLineAndStationRepository.findAll()
    }

    fun getTicketSales(): List<TicketSales> {
        authService.hasAuthority(RoleEnum.ROLE_ADMIN)
        return ticketSalesRepository.findAll()
    }

    fun getTotalIncome(): List<TotalIncome> {
        authService.hasAuthority(RoleEnum.ROLE_ADMIN)
        return totalIncomeRepository.findAll()
    }

    fun getAllUsers(): List<AdminUsersResponse> {
        authService.hasAuthority(RoleEnum.ROLE_ADMIN)
        return korisnikService.getAllUsers()
    }

    fun updateRolesForUser(
        id: Long,
        roleNames: List<String>,
    ): List<AdminUsersResponse> {
        authService.hasAuthority(RoleEnum.ROLE_ADMIN)
        val user = korisnikRepository.findByIdOrNull(id) ?: throw NotFoundException("User not found")
        val roles = roleNames.flatMap { role -> roleRepository.findAllByNameLikeIgnoreCase(role) }

        val allByKorisnik = korisnikRoleRepository.findAllByKorisnik(user)

        val rolesToDelete = allByKorisnik.filter { it.role !in roles }
        val rolesToAdd =
            roles.filter { it !in allByKorisnik.map { r -> r.role } }.map {
                KorisnikRole(
                    id = 0L,
                    korisnik = user,
                    role = it,
                )
            }

        korisnikRoleRepository.deleteAll(rolesToDelete)
        korisnikRoleRepository.saveAll(rolesToAdd)
        return getAllUsers()
    }
}
