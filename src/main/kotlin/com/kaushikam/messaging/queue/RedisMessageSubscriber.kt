package com.kaushikam.messaging.queue

import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import org.springframework.stereotype.Service

@Service
class RedisMessageSubscriber: MessageListener {

	companion object {
		@JvmStatic
		val messageList = mutableListOf<String>()
	}

	override fun onMessage(message: Message, pattern: ByteArray?) {
		messageList.add(message.toString())
		println("Message received: ${String(message.body)}")
	}
}