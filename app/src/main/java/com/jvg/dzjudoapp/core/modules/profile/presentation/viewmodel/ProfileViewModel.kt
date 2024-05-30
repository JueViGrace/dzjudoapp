package com.jvg.dzjudoapp.core.modules.profile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jvg.dzjudoapp.core.common.Constants.SHARING_STARTED
import com.jvg.dzjudoapp.core.modules.profile.presentation.state.ProfileState
import com.jvg.dzjudoapp.student.domain.usecase.GetStudents
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class ProfileViewModel(
    getStudents: GetStudents
) : ViewModel() {
    private var _state: MutableStateFlow<ProfileState> = MutableStateFlow(ProfileState())
    val state: StateFlow<ProfileState> = combine(
        _state,
        getStudents()
    ) { state, students ->
        state.copy(
            students = students
        )
    }.stateIn(
        viewModelScope,
        SHARING_STARTED,
        ProfileState()
    )
}
