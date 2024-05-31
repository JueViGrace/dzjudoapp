package com.jvg.dzjudoapp.core.modules.profile.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.jvg.dzjudoapp.core.modules.profile.presentation.state.ProfileState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileViewModel : ViewModel() {
    private var _state: MutableStateFlow<ProfileState> = MutableStateFlow(ProfileState())
    val state: StateFlow<ProfileState> = _state.asStateFlow()
}
