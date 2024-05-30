package com.jvg.dzjudoapp.student.presentation.events

sealed interface AddStudentEvent {
    data class OnNameChanged(val value: String) : AddStudentEvent
    data class OnLastNameChanged(val value: String) : AddStudentEvent
    data class OnBirthdayChanged(val value: String) : AddStudentEvent
    data class OnDniChanged(val value: String) : AddStudentEvent
    data class OnAddressChanged(val value: String) : AddStudentEvent
    data class OnPhoneChanged(val value: String) : AddStudentEvent
    data class OnEmailChanged(val value: String) : AddStudentEvent
    data class OnStartsDateChanged(val value: String) : AddStudentEvent
    data class OnBeltChanged(val value: String) : AddStudentEvent
    data class OnRepresentativeChanged(val value: String) : AddStudentEvent
    data class OnEmergencyPhoneChanged(val value: String) : AddStudentEvent
    data class OnActiveChanged(val value: Boolean) : AddStudentEvent
    data object SaveStudent : AddStudentEvent
    data object DismissStudent : AddStudentEvent
}
