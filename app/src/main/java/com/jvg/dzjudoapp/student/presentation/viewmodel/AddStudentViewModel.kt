package com.jvg.dzjudoapp.student.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jvg.dzjudoapp.core.state.RequestState
import com.jvg.dzjudoapp.student.domain.model.Student
import com.jvg.dzjudoapp.student.domain.usecase.AddStudent
import com.jvg.dzjudoapp.student.domain.usecase.GetStudent
import com.jvg.dzjudoapp.student.domain.usecase.ValidateStudent
import com.jvg.dzjudoapp.student.presentation.events.AddStudentEvent
import com.jvg.dzjudoapp.student.presentation.state.AddStudentState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class AddStudentViewModel(
    private val id: String,
    private val getStudent: GetStudent,
    private val addStudent: AddStudent,
    private val validateStudent: ValidateStudent,
    private val coroutineContext: CoroutineContext
) : ViewModel() {
    private var _state: MutableStateFlow<AddStudentState> = MutableStateFlow(AddStudentState())
    val state: StateFlow<AddStudentState> = _state.asStateFlow()

    init {
        if (id.isNotEmpty()) {
            viewModelScope.launch(coroutineContext) {
                getStudent(id).collect { result ->
                    when (result) {
                        is RequestState.Success -> {
                            _state.update { addStudentState ->
                                addStudentState.copy(
                                    newStudent = result.data
                                )
                            }
                        }
                        else -> RequestState.Idle
                    }
                }
            }
        }
    }

    private fun updateState() {
        viewModelScope.launch {
            validateStudent(_state.value.newStudent).collect { errors ->
                _state.update { addStudentState ->
                    addStudentState.copy(
                        nameError = errors.nameError,
                        lastnameError = errors.lastnameError,
                        birthdayError = errors.birthdayError,
                        dniError = errors.dniError,
                        addressError = errors.addressError,
                        phoneError = errors.phoneError,
                        emailError = errors.emailError,
                        startDateError = errors.startDateError,
                        beltError = errors.beltError,
                        representativeNameError = errors.representativeNameError,
                        emergencyPhoneError = errors.emergencyPhoneError,
                        errors = errors.errors.isNotEmpty()
                    )
                }
            }
        }
    }

    fun onEvent(event: AddStudentEvent) {
        when (event) {
            is AddStudentEvent.OnActiveChanged -> {
                _state.update { addStudentState ->
                    addStudentState.copy(
                        newStudent = addStudentState.newStudent.copy(active = event.value)
                    )
                }
            }
            is AddStudentEvent.OnAddressChanged -> {
                _state.update { addStudentState ->
                    addStudentState.copy(
                        newStudent = addStudentState.newStudent.copy(address = event.value)
                    )
                }
            }
            is AddStudentEvent.OnBeltChanged -> {
                _state.update { addStudentState ->
                    addStudentState.copy(
                        newStudent = addStudentState.newStudent.copy(belt = event.value)
                    )
                }
            }
            is AddStudentEvent.OnBirthdayChanged -> {
                _state.update { addStudentState ->
                    addStudentState.copy(
                        newStudent = addStudentState.newStudent.copy(birthday = event.value)
                    )
                }
            }
            is AddStudentEvent.OnDniChanged -> {
                _state.update { addStudentState ->
                    addStudentState.copy(
                        newStudent = addStudentState.newStudent.copy(dni = event.value)
                    )
                }
            }
            is AddStudentEvent.OnEmailChanged -> {
                _state.update { addStudentState ->
                    addStudentState.copy(
                        newStudent = addStudentState.newStudent.copy(email = event.value)
                    )
                }
            }
            is AddStudentEvent.OnEmergencyPhoneChanged -> {
                _state.update { addStudentState ->
                    addStudentState.copy(
                        newStudent = addStudentState.newStudent.copy(emergencyPhone = event.value)
                    )
                }
            }
            is AddStudentEvent.OnLastNameChanged -> {
                _state.update { addStudentState ->
                    addStudentState.copy(
                        newStudent = addStudentState.newStudent.copy(lastname = event.value)
                    )
                }
            }
            is AddStudentEvent.OnNameChanged -> {
                _state.update { addStudentState ->
                    addStudentState.copy(
                        newStudent = addStudentState.newStudent.copy(name = event.value)
                    )
                }
            }
            is AddStudentEvent.OnPhoneChanged -> {
                _state.update { addStudentState ->
                    addStudentState.copy(
                        newStudent = addStudentState.newStudent.copy(phone = event.value)
                    )
                }
            }
            is AddStudentEvent.OnRepresentativeChanged -> {
                _state.update { addStudentState ->
                    addStudentState.copy(
                        newStudent = addStudentState.newStudent.copy(representativeName = event.value)
                    )
                }
            }
            is AddStudentEvent.OnStartsDateChanged -> {
                _state.update { addStudentState ->
                    addStudentState.copy(
                        newStudent = addStudentState.newStudent.copy(startDate = event.value)
                    )
                }
            }
            AddStudentEvent.DismissStudent -> {
                _state.update { addStudentState ->
                    addStudentState.copy(
                        newStudent = Student(),
                        nameError = null,
                        lastnameError = null,
                        birthdayError = null,
                        dniError = null,
                        addressError = null,
                        phoneError = null,
                        emailError = null,
                        startDateError = null,
                        beltError = null,
                        representativeNameError = null,
                        emergencyPhoneError = null,
                        errors = null
                    )
                }
            }
            AddStudentEvent.SaveStudent -> {
                if (_state.value.errors == false) {
                    viewModelScope.launch(coroutineContext) {
                        addStudent(_state.value.newStudent)
                        onEvent(AddStudentEvent.DismissStudent)
                    }
                }
            }
        }
        updateState()
    }
}
