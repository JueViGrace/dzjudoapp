package com.jvg.dzjudoapp.student.presentation.state

import com.jvg.dzjudoapp.student.domain.model.Student

data class AddStudentState(
    val newStudent: Student = Student(),
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
    val errors: Boolean? = null
)
