package com.jvg.dzjudoapp.exam.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jvg.dzjudoapp.core.common.Constants.SHARING_STARTED
import com.jvg.dzjudoapp.exam.domain.usecase.GetExams
import com.jvg.dzjudoapp.exam.presentation.state.ExamsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class ExamsViewModel(
    getExams: GetExams
) : ViewModel() {
    private var _state: MutableStateFlow<ExamsState> = MutableStateFlow(ExamsState())
    val state = combine(
        _state,
        getExams()
    ) { state, exams ->
        state.copy(
            exams = exams
        )
    }.stateIn(
        viewModelScope,
        SHARING_STARTED,
        ExamsState()
    )
}
