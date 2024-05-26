package com.jvg.dzjudoapp.student.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jvg.dzjudoapp.core.common.Constants.SHARING_STARTED
import com.jvg.dzjudoapp.student.domain.repository.StudentRepository
import com.jvg.dzjudoapp.student.presentation.state.StudentsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class StudentsViewModel(
    private val studentRepository: StudentRepository
) : ViewModel() {
    private var _state: MutableStateFlow<StudentsState> = MutableStateFlow(StudentsState())
    val state = combine(
        _state,
        studentRepository.getStudents()
    ) { state, students ->
        state.copy(
            students = students
        )
    }.stateIn(
        viewModelScope,
        SHARING_STARTED,
        StudentsState()
    )
}
