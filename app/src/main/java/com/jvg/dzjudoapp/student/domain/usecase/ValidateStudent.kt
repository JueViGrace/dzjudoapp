package com.jvg.dzjudoapp.student.domain.usecase

import com.jvg.dzjudoapp.student.domain.model.Student
import com.jvg.dzjudoapp.student.domain.rules.StudentValidator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

class ValidateStudent(
    private val coroutineContext: CoroutineContext
) {
    operator fun invoke(student: Student): Flow<StudentValidator.StudentValidationResult> = flow {
        var result = StudentValidator.validateStudent(student)
        val errors = listOfNotNull(
            result.nameError,
            result.lastnameError,
            result.birthdayError,
            result.dniError,
            result.addressError,
            result.phoneError,
            result.emailError,
            result.startDateError,
            result.beltError,
            result.representativeNameError,
            result.emergencyPhoneError,
        )

        result = result.copy(
            errors = errors
        )

        emit(result)
    }.flowOn(coroutineContext)
}
