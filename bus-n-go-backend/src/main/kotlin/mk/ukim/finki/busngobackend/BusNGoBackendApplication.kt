package mk.ukim.finki.busngobackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.event.ApplicationEventMulticaster
import org.springframework.context.event.SimpleApplicationEventMulticaster
import org.springframework.core.task.SimpleAsyncTaskExecutor
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@SpringBootApplication
@EnableAsync
@EnableScheduling
class BusNGoBackendApplication

fun main(args: Array<String>) {
    runApplication<BusNGoBackendApplication>(*args)
}

@Bean
fun encoder(): PasswordEncoder = NoOpPasswordEncoder.getInstance()
//    object : PasswordEncoder {
//        override fun encode(rawPassword: CharSequence?): String = rawPassword.toString()
//
//        override fun matches(
//            rawPassword: CharSequence?,
//            encodedPassword: String?,
//        ): Boolean = rawPassword != null && rawPassword.toString() == encodedPassword
//    }

@Bean
fun asyncEventPublisher(): ApplicationEventMulticaster {
    val publisher = SimpleApplicationEventMulticaster()
    publisher.setTaskExecutor(SimpleAsyncTaskExecutor())
    return publisher
}
