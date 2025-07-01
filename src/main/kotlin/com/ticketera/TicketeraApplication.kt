package com.ticketera

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class TicketeraApplication

fun main(args: Array<String>) {
	runApplication<TicketeraApplication>(*args)
}
