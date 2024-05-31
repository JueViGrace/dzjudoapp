package com.jvg.dzjudoapp.exam.domain.usecase

import com.jvg.dzjudoapp.exam.domain.model.Exam
import com.jvg.dzjudoapp.exam.domain.repository.ExamRepository

class AddExam(
    private val examRepository: ExamRepository,
) {
    suspend operator fun invoke(exam: Exam) {
        examRepository.addExams(exam)
    }
}