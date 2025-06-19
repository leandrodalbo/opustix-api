package com.ticketera.ticketera

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TicketeraApplication

fun main(args: Array<String>) {
	runApplication<TicketeraApplication>(*args)
}
