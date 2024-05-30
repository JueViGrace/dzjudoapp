package com.jvg.dzjudoapp.student.presentation.navigation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.jvg.dzjudoapp.R
import com.jvg.dzjudoapp.core.presentation.components.CustomText
import com.jvg.dzjudoapp.core.presentation.components.DatePickerComponent
import com.jvg.dzjudoapp.core.presentation.components.DefaultBackArrow
import com.jvg.dzjudoapp.core.presentation.components.DefaultLayoutComponent
import com.jvg.dzjudoapp.core.presentation.components.DefaultTopBar
import com.jvg.dzjudoapp.core.presentation.components.TextFieldComponent
import com.jvg.dzjudoapp.student.presentation.events.AddStudentEvent
import com.jvg.dzjudoapp.student.presentation.viewmodel.AddStudentViewModel
import org.koin.androidx.compose.koinViewModel

class AddStudentScreen : Screen {
    override val key: ScreenKey = super.key + uniqueScreenKey

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = koinViewModel<AddStudentViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()

        println(state)

        DefaultLayoutComponent(
            topBar = {
                DefaultTopBar(
                    title = {
                        CustomText(
                            text = if (
                                state.student.name.isNotBlank() &&
                                state.student.lastname.isNotBlank()
                            ) {
                                "${state.student.name} ${state.student.lastname}"
                            } else {
                                stringResource(R.string.create_a_new_student)
                            },
                            fontSize = MaterialTheme.typography.titleLarge.fontSize,
                            fontWeight = MaterialTheme.typography.titleLarge.fontWeight
                        )
                    },
                    navigationIcon = {
                        DefaultBackArrow {
                            viewModel.onEvent(AddStudentEvent.DismissStudent)
                            navigator.pop()
                        }
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        viewModel.onEvent(AddStudentEvent.SaveStudent)
                        if (viewModel.getErrors()) {
                            navigator.pop()
                            viewModel.onEvent(AddStudentEvent.DismissStudent)
                        }
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_check_24px),
                        contentDescription = "Check"
                    )
                }
            }
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Image(
                        modifier = Modifier.size(80.dp),
                        painter = painterResource(id = R.drawable.ic_account_circle_24px),
                        contentDescription = "Person"
                    )
                }

                item {
                    TextFieldComponent(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 5.dp),
                        value = state.student.name,
                        newValue = { newValue ->
                            viewModel.onEvent(AddStudentEvent.OnNameChanged(newValue))
                        },
                        label = R.string.name,
                        placeholder = R.string.name_of_the_student,
                        supportingText = state.nameError,
                        errorStatus = state.nameError != null,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next,
                            keyboardType = KeyboardType.Text,
                            capitalization = KeyboardCapitalization.Words
                        ),
                    )
                }

                item {
                    TextFieldComponent(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 5.dp),
                        value = state.student.lastname,
                        newValue = { newValue ->
                            viewModel.onEvent(AddStudentEvent.OnLastNameChanged(newValue))
                        },
                        label = R.string.last_name,
                        placeholder = R.string.last_name_of_the_student,
                        supportingText = state.lastnameError,
                        errorStatus = state.lastnameError != null,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next,
                            keyboardType = KeyboardType.Text,
                            capitalization = KeyboardCapitalization.Words
                        ),
                    )
                }

                item {
                    DatePickerComponent(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 5.dp),
                        label = R.string.birthday,
                        icon = R.drawable.ic_event_24px,
                        value = state.student.birthday,
                        onTextSelected = { newValue ->
                            viewModel.onEvent(AddStudentEvent.OnBirthdayChanged(newValue))
                        }
                    )
                }

                item {
                    TextFieldComponent(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 5.dp),
                        value = state.student.dni,
                        newValue = { newValue ->
                            viewModel.onEvent(AddStudentEvent.OnDniChanged(newValue))
                        },
                        label = R.string.dni,
                        placeholder = R.string.student_s_dni,
                        supportingText = state.dniError,
                        errorStatus = state.dniError != null,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next,
                            keyboardType = KeyboardType.Number,
                            capitalization = KeyboardCapitalization.Words
                        ),
                    )
                }

                item {
                    TextFieldComponent(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 5.dp),
                        value = state.student.address,
                        newValue = { newValue ->
                            viewModel.onEvent(AddStudentEvent.OnAddressChanged(newValue))
                        },
                        label = R.string.address,
                        placeholder = R.string.student_s_address,
                        supportingText = state.addressError,
                        errorStatus = state.addressError != null,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next,
                            keyboardType = KeyboardType.Text,
                            capitalization = KeyboardCapitalization.Words
                        ),
                    )
                }

                item {
                    TextFieldComponent(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 5.dp),
                        value = state.student.phone,
                        newValue = { newValue ->
                            viewModel.onEvent(AddStudentEvent.OnPhoneChanged(newValue))
                        },
                        label = R.string.phone,
                        placeholder = R.string.student_s_phone,
                        supportingText = state.phoneError,
                        errorStatus = state.phoneError != null,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next,
                            keyboardType = KeyboardType.Phone,
                            capitalization = KeyboardCapitalization.Words
                        ),
                    )
                }

                item {
                    TextFieldComponent(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 5.dp),
                        value = state.student.email,
                        newValue = { newValue ->
                            viewModel.onEvent(AddStudentEvent.OnEmailChanged(newValue))
                        },
                        label = R.string.email,
                        placeholder = R.string.student_s_email,
                        supportingText = state.emailError,
                        errorStatus = state.emailError != null,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next,
                            keyboardType = KeyboardType.Email,
                            capitalization = KeyboardCapitalization.None
                        ),
                    )
                }

                item {
                    DatePickerComponent(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 5.dp),
                        label = R.string.start_date,
                        icon = R.drawable.ic_calendar_month_24px,
                        value = state.student.startDate,
                        onTextSelected = { newValue ->
                            viewModel.onEvent(AddStudentEvent.OnStartsDateChanged(newValue))
                        }
                    )
                }

                item {
                    TextFieldComponent(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 5.dp),
                        value = state.student.belt,
                        newValue = { newValue ->
                            viewModel.onEvent(AddStudentEvent.OnBeltChanged(newValue))
                        },
                        label = R.string.belt,
                        placeholder = R.string.student_s_belt,
                        supportingText = state.beltError,
                        errorStatus = state.beltError != null,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next,
                            keyboardType = KeyboardType.Text,
                            capitalization = KeyboardCapitalization.Words
                        ),
                    )
                }

                item {
                    TextFieldComponent(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 5.dp),
                        value = state.student.representativeName,
                        newValue = { newValue ->
                            viewModel.onEvent(AddStudentEvent.OnRepresentativeChanged(newValue))
                        },
                        label = R.string.representative_name,
                        placeholder = R.string.student_s_representative_name,
                        supportingText = state.representativeNameError,
                        errorStatus = state.representativeNameError != null,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next,
                            keyboardType = KeyboardType.Text,
                            capitalization = KeyboardCapitalization.Words
                        ),
                    )
                }

                item {
                    TextFieldComponent(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 5.dp),
                        value = state.student.emergencyPhone,
                        newValue = { newValue ->
                            viewModel.onEvent(AddStudentEvent.OnEmergencyPhoneChanged(newValue))
                        },
                        label = R.string.emergency_phone,
                        placeholder = R.string.student_s_emergency_phone,
                        supportingText = state.emergencyPhoneError,
                        errorStatus = state.emergencyPhoneError != null,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Phone,
                            capitalization = KeyboardCapitalization.Words
                        ),
                    )
                }

                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(all = 10.dp),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CustomText(text = "${stringResource(R.string.active)}:")

                        Checkbox(
                            checked = state.student.active,
                            onCheckedChange = { newValue ->
                                viewModel.onEvent(AddStudentEvent.OnActiveChanged(newValue))
                            }
                        )
                    }
                }
            }
        }
    }
}
