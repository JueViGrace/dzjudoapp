package com.jvg.dzjudoapp.student.domain.model

data class Student(
    val id: String = "",
    val name: String = "",
    val lastname: String = "",
    val birthday: String = "",
    val address: String = "",
    val phone: String = "",
    val email: String = "",
    val startDate: String = "",
    val belt: String = "",
    val representativeName: String = "",
    val emergencyPhone: String = "",
    val active: Boolean = true,
)
