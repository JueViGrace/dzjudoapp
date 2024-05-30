package com.jvg.dzjudoapp.student.domain.usecase

import com.jvg.dzjudoapp.student.domain.repository.StudentRepository

class DeleteStudent(
    private val studentRepository: StudentRepository
) {
    suspend operator fun invoke(id: String) {
        studentRepository.deleteStudent(id)
    }
}