package com.jvg.dzjudoapp.student.domain.rules

import android.util.Patterns
import com.jvg.dzjudoapp.R
import com.jvg.dzjudoapp.core.common.Constants.PHONE_LENGTH
import com.jvg.dzjudoapp.student.domain.model.Student

object StudentValidator {
    fun validateStudent(student: Student): StudentValidationResult {
        var result = StudentValidationResult()

        if (student.name.isBlank()) {
            result = result.copy(nameError = R.string.name_must_not_be_empty)
        } else {
            result = result.copy(nameError = null)
        }

        if (student.lastname.isBlank()) {
            result = result.copy(lastnameError = R.string.last_name_must_not_be_empty)
        }

        if (student.birthday.isBlank()) {
            result = result.copy(birthdayError = R.string.birth_day_must_not_be_empty)
        }

        if (student.dni.map { it.isDigit() }.contains(false)) {
            result = result.copy(dniError = R.string.dni_must_be_only_numbers)
        } else if (student.dni.isBlank()) {
            result = result.copy(dniError = R.string.dni_must_not_be_empty)
        }

        if (student.address.isBlank()) {
            result = result.copy(addressError = R.string.address_must_not_be_empty)
        }

        if (student.phone.length < PHONE_LENGTH) {
            result = result.copy(phoneError = R.string.phone_must_be_at_least_10_digits_long)
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(student.email).matches()) {
            result = result.copy(emailError = R.string.email_must_be_a_valid_email)
        }

        if (student.startDate.isBlank()) {
            result = result.copy(startDateError = R.string.start_date_must_not_be_empty)
        }

        if (student.belt.isBlank()) {
            result = result.copy(beltError = R.string.belt_must_not_be_empty)
        }

        if (student.representativeName.isBlank()) {
            result = result.copy(representativeNameError = R.string.representative_name_must_not_be_empty)
        }

        if (student.emergencyPhone.length < PHONE_LENGTH) {
            result = result.copy(emergencyPhoneError = R.string.emergency_phone_must_be_at_least_10_digits_long)
        }

        return result
    }

    data class StudentValidationResult(
        val nameError: Int? = null,
        val lastnameError: Int? = null,
        val birthdayError: Int? = null,
        val dniError: Int? = null,
        val addressError: Int? = null,
        val phoneError: Int? = null,
        val emailError: Int? = null,
        val startDateError: Int? = null,
        val beltError: Int? = null,
        val representativeNameError: Int? = null,
        val emergencyPhoneError: Int? = null,
        val errors: List<Int> = emptyList()
    )
}
