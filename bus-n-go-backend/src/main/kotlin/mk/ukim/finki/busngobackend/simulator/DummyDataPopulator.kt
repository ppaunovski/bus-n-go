package mk.ukim.finki.busngobackend.simulator

import mk.ukim.finki.busngobackend.domain.entities.*
import mk.ukim.finki.busngobackend.domain.enums.BiletEnum
import mk.ukim.finki.busngobackend.domain.enums.VozenjeStatus
import mk.ukim.finki.busngobackend.repository.*
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.concurrent.ThreadLocalRandom

@Service
class DummyDataPopulator(
    private val linijaRepository: LinijaRepository,
    private val vozacRepository: VozacRepository,
    private val avtobusRepository: AvtobusRepository,
    private val linijaPravecRepository: LinijaPravecRepository,
    private val postojkaNaLinijaRepository: PostojkaNaLinijaRepository,
    private val instancaNaLinijaRepository: InstancaNaLinijaRepository,
    private val instancaNaLinijaPostojkaNaLinijaRepository: InstancaNaLinijaPostojkaNaLinijaRepository,
    private val vozenjeRepository: VozenjeRepository,
    private val patnikRepository: PatnikRepository,
    private val biletRepository: BiletRepository,
    private val tipbiletRepository: TipbiletRepository,
) {
    fun populateCommutes() {
        val routeInstances = instancaNaLinijaRepository.findAll()
        routeInstances.forEach { instancaNaLinija ->
            run {
                val patnik = patnikRepository.findAll().random()
                val startDate =
                    getRandomTimestamp(
                        instancaNaLinija.startDate.toLocalDateTime(),
                        instancaNaLinija.endDate!!.toLocalDateTime(),
                    )
                val bilet =
                    biletRepository.findAll().randomOrNull() ?: biletRepository.save(
                        Bilet(
                            id = 0L,
                            datumKupuvanje = startDate,
                            datumAktivacija = startDate,
                            status = BiletEnum.ACTIVE,
                            patnik = patnik,
                            tip = tipbiletRepository.findAll().random(),
                        ),
                    )
                val postojkaNaLinijaStart =
                    instancaNaLinijaPostojkaNaLinijaRepository.findByInstancaNaLinijaAndTimestampNearestTo(
                        instancaNaLinija.id,
                        startDate,
                    )
                vozenjeRepository.save(
                    Vozenje(
                        id = 0L,
                        startDate = startDate,
                        instancaNaLinija = instancaNaLinija,
                        postojkaNaLinijaStart =
                            postojkaNaLinijaStart?.postojkaNaLinija
                                ?: postojkaNaLinijaRepository.findByLinija(instancaNaLinija.linija).random(),
                        patnik = patnik,
                        endDate = instancaNaLinija.endDate,
                        bilet = bilet,
                        status = VozenjeStatus.FINISHED,
                    ),
                )
            }
        }
    }

    fun populateRouteInstances() {
        val lines = linijaRepository.findAll()
        val drivers = vozacRepository.findAll()
        val buses = avtobusRepository.findAll()

        lines.forEach { randomLine ->
            run {
                val directionsForRandomLine = linijaPravecRepository.findAllByLinija(randomLine).map { it.pravec }
                for (i in 0..10) {
                    val randomDirection = directionsForRandomLine.random()
                    val startDate =
                        getRandomTimestamp(
                            start = LocalDateTime.of(2020, 1, 1, 0, 0),
                            end = LocalDateTime.now(),
                        )

                    val instance =
                        instancaNaLinijaRepository.save(
                            InstancaNaLinija(
                                startDate = startDate,
                                endDate = null,
                                id = 0L,
                                vozac = drivers.random(),
                                linija = randomLine,
                                avtobus = buses.random(),
                                pravec = randomDirection,
                            ),
                        )

                    val stationsForRandomLine =
                        postojkaNaLinijaRepository
                            .findByLinijaAndPravec(
                                randomLine,
                                randomDirection,
                            ).sortedBy { it.redenBroj }
                    var lastTime = Timestamp.valueOf(LocalDateTime.now())
                    stationsForRandomLine.forEach { station ->
                        run {
                            if (station.redenBroj.toInt() == 1) {
                                val start = instance.startDate.toLocalDateTime().plusMinutes(1)
                                lastTime = getRandomTimestamp(start = start, end = start.plusMinutes(12))
                            } else {
                                val start = lastTime.toLocalDateTime().plusMinutes(1)
                                lastTime = getRandomTimestamp(start = start, end = start.plusMinutes(12))
                            }
                            instancaNaLinijaPostojkaNaLinijaRepository.save(
                                InstancaNaLinijaPostojkaNaLinija(
                                    id = 0L,
                                    instancaNaLinija = instance,
                                    postojkaNaLinija = station,
                                    timestamp = lastTime,
                                ),
                            )
                        }
                    }
                    instance.endDate = lastTime
                    instancaNaLinijaRepository.save(instance)
                }
            }
        }
    }

    private fun getRandomTimestamp(
        start: LocalDateTime,
        end: LocalDateTime,
    ): Timestamp {
        // Define the start time (January 1, 2020, 00:00:00)
//        val start = LocalDateTime.of(2020, 1, 1, 0, 0)
        // Get the current time
//        val end = LocalDateTime.now()

        // Convert LocalDateTime to milliseconds since epoch
        val startMillis = start.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
        val endMillis = end.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()

        // Generate a random time between startMillis and endMillis
        val randomMillis = ThreadLocalRandom.current().nextLong(startMillis, endMillis)

        // Convert milliseconds back to LocalDateTime
        val randomLocalDateTime =
            LocalDateTime.ofInstant(
                java.time.Instant.ofEpochMilli(randomMillis),
                ZoneId.systemDefault(),
            )

        // Convert LocalDateTime to Timestamp
        return Timestamp.valueOf(randomLocalDateTime)
    }
}
