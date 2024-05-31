package com.jvg.dzjudoapp.exam.domain.rules

import com.jvg.dzjudoapp.R
import com.jvg.dzjudoapp.exam.domain.model.Exam

object ExamValidator {

    fun validateExam(exam: Exam): ExamValidationResult {
        var result = ExamValidationResult()

        if (exam.name.isBlank()) {
            result = result.copy(
                nameError = R.string.exam_name_must_not_be_empty
            )
        }

        return result
    }

    data class ExamValidationResult(
        val nameError: Int? = null,
        val errors: List<Int> = emptyList()
    )
}
