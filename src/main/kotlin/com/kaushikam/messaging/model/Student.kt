package com.kaushikam.messaging.model

import org.springframework.data.redis.core.RedisHash

@RedisHash("Student")
data class Student (
	val id: String,
	val name: String,
	val gender: Gender,
	val grade: Int
)

enum class Gender {
	MALE, FEMALE
}