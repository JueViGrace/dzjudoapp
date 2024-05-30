package com.jvg.dzjudoapp.student.domain.usecase

import com.jvg.dzjudoapp.student.domain.repository.StudentRepository

class ActiveStudent(
    private val studentRepository: StudentRepository,
) {
    suspend operator fun invoke(active: Boolean, id: String) {
        studentRepository.activeStudents(active, id)
    }
}
