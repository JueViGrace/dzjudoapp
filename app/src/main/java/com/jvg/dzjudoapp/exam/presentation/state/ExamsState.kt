package com.jvg.dzjudoapp.exam.presentation.state

import com.jvg.dzjudoapp.core.state.RequestState
import com.jvg.dzjudoapp.exam.domain.model.Exam

data class ExamsState(
    val exams: RequestState<List<Exam>> = RequestState.Loading
)
