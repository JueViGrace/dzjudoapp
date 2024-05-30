package com.jvg.dzjudoapp.core.modules.profile.presentation.state

import com.jvg.dzjudoapp.core.state.RequestState
import com.jvg.dzjudoapp.student.domain.model.Student

data class ProfileState(
    val students: RequestState<List<Student>> = RequestState.Idle
)
