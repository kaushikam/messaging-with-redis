package com.kaushikam.messaging.config

import com.kaushikam.messaging.queue.MessagePublisher
import com.kaushikam.messaging.queue.RedisMessagePublisher
import com.kaushikam.messaging.queue.RedisMessageSubscriber
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.data.redis.connection.RedisPassword
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter
import org.springframework.data.redis.serializer.GenericToStringSerializer

@Configuration
@ComponentScan("com.kaushikam.messaging")
@PropertySource("classpath:application.properties")
class RedisConfig (
	@Value("\${spring.redis.password}")
	val password: String
) {

	@Bean
	fun jedisConnectionFactory(): JedisConnectionFactory {
		val configuration = RedisStandaloneConfiguration()
		configuration.password = RedisPassword.of(password)
		return JedisConnectionFactory(configuration)
	}

	@Bean
	fun redisTemplate(): RedisTemplate<String, Any> {
		val template = RedisTemplate<String, Any>()
		template.setConnectionFactory(jedisConnectionFactory())
		template.valueSerializer = GenericToStringSerializer(Any::class.java)
		return template
	}

	@Bean
	fun messageListener(): MessageListenerAdapter {
		return MessageListenerAdapter(RedisMessageSubscriber())
	}

	@Bean
	fun redisContainer(): RedisMessageListenerContainer {
		val container = RedisMessageListenerContainer()
		container.setConnectionFactory(jedisConnectionFactory())
		container.addMessageListener(messageListener(), topic())
		return container
	}

	@Bean
	fun redisPublisher(): MessagePublisher {
		return RedisMessagePublisher(redisTemplate(), topic())
	}

	@Bean
	fun topic(): ChannelTopic {
		return ChannelTopic("pubsub:queue")
	}
}