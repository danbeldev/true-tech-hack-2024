package ru.mtc.live

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MtcLiveApplication

fun main(args: Array<String>) {
    runApplication<MtcLiveApplication>(*args)
}
