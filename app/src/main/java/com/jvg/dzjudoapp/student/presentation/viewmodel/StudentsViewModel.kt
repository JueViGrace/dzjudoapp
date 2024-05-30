package com.jvg.dzjudoapp.student.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jvg.dzjudoapp.core.common.Constants.SHARING_STARTED
import com.jvg.dzjudoapp.core.state.RequestState
import com.jvg.dzjudoapp.student.domain.model.Student
import com.jvg.dzjudoapp.student.domain.usecase.GetStudents
import com.jvg.dzjudoapp.student.presentation.state.StudentsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class StudentsViewModel(
    getStudents: GetStudents
) : ViewModel() {
    private var _state: MutableStateFlow<StudentsState> = MutableStateFlow(StudentsState())
    val state = combine(
        _state,
        getStudents()
    ) { state, students ->
        when (students) {
            is RequestState.Error -> {
                state.copy(
                    students = students
                )
            }
            is RequestState.Success -> {
                if (state.searchText.isBlank()) {
                    state.copy(
                        students = students
                    )
                } else {
                    val data = students.data.filter { student: Student ->
                        student.name.lowercase().contains(state.searchText.trim().lowercase()) ||
                            student.lastname.lowercase().contains(state.searchText.trim().lowercase()) ||
                            student.email.lowercase().contains(state.searchText.trim().lowercase())
                    }

                    state.copy(
                        students = RequestState.Success(data)
                    )
                }
            }
            else -> {
                state.copy(
                    students = students
                )
            }
        }
    }.stateIn(
        viewModelScope,
        SHARING_STARTED,
        StudentsState()
    )

    fun onSearchTextChange(text: String) {
        _state.update { studentsState ->
            studentsState.copy(
                searchText = text
            )
        }

        if (_state.value.searchText.isEmpty()) {
            toggleVisibility(false)
        }
    }

    fun toggleVisibility(force: Boolean? = null) {
        _state.update { studentsState ->
            studentsState.copy(
                searchBarVisible = force ?: studentsState.searchBarVisible
            )
        }
    }
}
