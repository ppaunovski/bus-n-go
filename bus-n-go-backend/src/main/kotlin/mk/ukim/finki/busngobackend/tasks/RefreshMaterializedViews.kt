package mk.ukim.finki.busngobackend.tasks

import mk.ukim.finki.busngobackend.service.MaterializedViewService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class RefreshMaterializedViews(
    private val materializedViewService: MaterializedViewService,
) {
    @Scheduled(fixedRateString = "\${materialized.view.refresh.rate}")
    fun refreshMaterializedViewTask() {
        materializedViewService.refreshAverageTimeDiffs()
        materializedViewService.refreshCommutesByHour()
        materializedViewService.refreshFinesPerLine()
        materializedViewService.refreshTotalIncome()
    }
}
