package team.asteria.slay

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication
class SlayApplication

fun main(args: Array<String>) {
    runApplication<SlayApplication>(*args)
}
