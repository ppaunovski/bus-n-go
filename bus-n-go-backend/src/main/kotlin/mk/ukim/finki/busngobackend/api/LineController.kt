package mk.ukim.finki.busngobackend.api

import mk.ukim.finki.busngobackend.service.DirectionService
import mk.ukim.finki.busngobackend.service.LineService
import mk.ukim.finki.busngobackend.service.StationService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/lines")
class LineController(
    private val lineService: LineService,
    private val directionService: DirectionService,
    private val stationService: StationService,
) {
    @GetMapping("")
    fun getLines() = lineService.findAllLines()

    @GetMapping("/{id}/directions")
    fun getLineDirections(
        @PathVariable("id") lineId: Int,
    ) = directionService.findDirectionsByLineId(lineId)

    @GetMapping("/{lineId}/stations")
    fun getLineStations(
        @PathVariable("lineId") lineId: Int,
        @RequestParam("directionId", required = false) directionId: Long,
    ) = stationService.findStationsByLineIdAndDirectionId(lineId, directionId)
}
