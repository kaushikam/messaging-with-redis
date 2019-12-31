package com.kaushikam.messaging.queue

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.stereotype.Service

@Service
class RedisMessagePublisher (
	@Autowired
	private val redisTemplate: RedisTemplate<String, Any>,

	@Autowired
	private val channelTopic: ChannelTopic
): MessagePublisher {

	override fun publish(message: String) {
		redisTemplate.convertAndSend(channelTopic.topic, message)
	}
}