package com.jvg.dzjudoapp.exam.domain.usecase

import com.jvg.dzjudoapp.exam.domain.model.Exam
import com.jvg.dzjudoapp.exam.domain.rules.ExamValidator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

class ValidateExam(
    private val coroutineContext: CoroutineContext
) {
    operator fun invoke(exam: Exam): Flow<ExamValidator.ExamValidationResult> = flow {
        var result = ExamValidator.validateExam(exam)
        val errors = listOfNotNull(
            result.nameError
        )

        result = result.copy(
            errors = errors
        )

        emit(result)
    }.flowOn(coroutineContext)
}
