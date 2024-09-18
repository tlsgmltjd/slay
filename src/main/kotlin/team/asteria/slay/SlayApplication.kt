package team.asteria.slay

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SlayApplication

fun main(args: Array<String>) {
    runApplication<SlayApplication>(*args)
}
