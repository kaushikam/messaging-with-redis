package com.kaushikam.messaging

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MessagingWithRedisApplication

fun main(args: Array<String>) {
	runApplication<MessagingWithRedisApplication>(*args)
}
