package com.kaushikam.messaging.repo

import com.kaushikam.messaging.model.Student
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface StudentRepository: CrudRepository<Student, String>