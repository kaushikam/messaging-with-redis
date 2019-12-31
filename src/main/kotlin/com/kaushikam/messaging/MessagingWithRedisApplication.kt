package com.kaushikam.messaging

import com.kaushikam.messaging.queue.MessagePublisher
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class MessagingWithRedisApplication {
	@Bean
	fun demo(
		publisher: MessagePublisher
	): CommandLineRunner {
		return CommandLineRunner {
			publisher.publish("Test message")
		}
	}
}

fun main(args: Array<String>) {
	runApplication<MessagingWithRedisApplication>(*args)
}
