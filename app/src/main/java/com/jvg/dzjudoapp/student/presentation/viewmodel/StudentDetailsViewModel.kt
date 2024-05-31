package com.jvg.dzjudoapp.student.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jvg.dzjudoapp.core.common.Constants.SHARING_STARTED
import com.jvg.dzjudoapp.student.domain.usecase.GetStudent
import com.jvg.dzjudoapp.student.presentation.state.StudentDetailsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class StudentDetailsViewModel(
    val id: String,
    getStudent: GetStudent
) : ViewModel() {
    private var _state: MutableStateFlow<StudentDetailsState> = MutableStateFlow(StudentDetailsState())
    val state = combine(
        _state,
        getStudent(id),
    ) { state, student ->
        state.copy(
            student = student
        )
    }.stateIn(
        viewModelScope,
        SHARING_STARTED,
        StudentDetailsState()
    )
}
