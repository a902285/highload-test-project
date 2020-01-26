package dev.lysov.sn

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SnApplication

fun main(args: Array<String>) {
	runApplication<SnApplication>(*args)
}
