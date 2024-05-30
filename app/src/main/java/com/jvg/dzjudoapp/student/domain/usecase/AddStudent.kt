package com.jvg.dzjudoapp.student.domain.usecase

import com.jvg.dzjudoapp.student.domain.model.Student
import com.jvg.dzjudoapp.student.domain.repository.StudentRepository

class AddStudent(
    private val studentRepository: StudentRepository
) {
    suspend operator fun invoke(student: Student) {
        studentRepository.addStudent(student)
    }
}
