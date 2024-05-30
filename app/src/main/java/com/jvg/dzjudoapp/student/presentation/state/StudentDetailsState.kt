package com.jvg.dzjudoapp.student.presentation.state

import com.jvg.dzjudoapp.core.state.RequestState
import com.jvg.dzjudoapp.student.domain.model.Student

data class StudentDetailsState(
    val student: RequestState<Student> = RequestState.Loading
)
