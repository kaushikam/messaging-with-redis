package com.kaushikam.messaging.queue

interface MessagePublisher {
	fun publish(message: String)
}