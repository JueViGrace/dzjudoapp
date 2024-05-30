package com.jvg.dzjudoapp.student.domain.usecase

import com.jvg.dzjudoapp.student.domain.model.Student
import com.jvg.dzjudoapp.student.domain.repository.StudentRepository
import com.jvg.dzjudoapp.student.domain.rules.StudentValidator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AddStudent(
    private val studentRepository: StudentRepository,
) {
    operator fun invoke(student: Student): Flow<StudentValidator.StudentValidationResult> = flow {
        val result = StudentValidator.validateStudent(student)
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

        if (errors.isEmpty()) {
            studentRepository.addStudent(student)
            emit(result)
        } else {
            emit(result)
        }
    }
}
