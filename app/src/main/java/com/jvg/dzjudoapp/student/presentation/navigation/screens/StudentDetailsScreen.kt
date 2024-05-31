package com.jvg.dzjudoapp.student.presentation.navigation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.jvg.dzjudoapp.R
import com.jvg.dzjudoapp.core.presentation.actions.TopBarActions
import com.jvg.dzjudoapp.core.presentation.components.CustomClickableCard
import com.jvg.dzjudoapp.core.presentation.components.CustomText
import com.jvg.dzjudoapp.core.presentation.components.DefaultBackArrow
import com.jvg.dzjudoapp.core.presentation.components.DefaultStateLayoutComponent
import com.jvg.dzjudoapp.core.presentation.components.DefaultTopBar
import com.jvg.dzjudoapp.core.presentation.components.DefaultTopBarActions
import com.jvg.dzjudoapp.student.domain.model.Student
import com.jvg.dzjudoapp.student.presentation.viewmodel.StudentDetailsViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

data class StudentDetailsScreen(
    val id: String
) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = koinViewModel<StudentDetailsViewModel>(parameters = { parametersOf(id) })
        val state by viewModel.state.collectAsStateWithLifecycle()

        var title by rememberSaveable {
            mutableStateOf("")
        }

        var id: String by rememberSaveable {
            mutableStateOf("")
        }

        DefaultStateLayoutComponent(
            topBar = {
                DefaultTopBar(
                    title = {
                        CustomText(
                            text = title,
                            fontSize = MaterialTheme.typography.titleLarge.fontSize,
                            fontWeight = MaterialTheme.typography.titleLarge.fontWeight
                        )
                    },
                    navigationIcon = {
                        DefaultBackArrow {
                            navigator.pop()
                        }
                    },
                    actions = {
                        DefaultTopBarActions(
                            onMenuClick = { action ->
                                when {
                                    action is TopBarActions.Edit && id.isNotEmpty() -> {
                                        navigator.push(AddStudentScreen(id))
                                    }
                                }
                            },
                            items = listOf(TopBarActions.Edit)
                        )
                    }
                )
            },
            state = state.student
        ) { student: Student ->
            title = "${student.name} ${student.lastname}"
            id = student.id

            val active = stringResource(
                if (student.active) {
                    R.string.active_student
                } else {
                    R.string.inactive_student
                }
            )

            val column1 =
                mapOf(
                    Pair(R.string.birthday, student.birthday),
                    Pair(R.string.start_date, student.startDate),
                    Pair(R.string.dni, student.dni),
                    Pair(R.string.active, active),
                )

            val column2 = mapOf(
                Pair(R.string.address, student.address),
                Pair(R.string.email, student.email),
                Pair(R.string.phone, student.phone),
                Pair(R.string.emergency_phone, student.emergencyPhone),
                Pair(R.string.representative_name, student.representativeName),
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    // TODO: CHANGE CIRCLE COLOR FOR BELT COLOR

                    CustomClickableCard(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        shape = CircleShape,
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CustomText(
                                text = "${student.name} ${student.lastname}"
                            )
                            CustomText(
                                text = "${stringResource(id = R.string.belt)}: ${student.belt}"
                            )
                        }
                    }
                }

                item {
                    CustomClickableCard(
                        modifier = Modifier
                            .fillParentMaxSize(),
                        shape = RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp),
                        colors = CardDefaults.elevatedCardColors().copy(
                            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
                        )
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(start = 10.dp, end = 10.dp, top = 20.dp),
                                horizontalArrangement = Arrangement.spacedBy(
                                    10.dp,
                                    Alignment.CenterHorizontally
                                ),
                                verticalAlignment = Alignment.Top
                            ) {
                                Column(
                                    modifier = Modifier.weight(1f),
                                    verticalArrangement = Arrangement.spacedBy(
                                        10.dp,
                                        Alignment.CenterVertically
                                    ),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    column1.forEach { (title, value) ->
                                        CustomClickableCard(
                                            shape = RoundedCornerShape(10)
                                        ) {
                                            Column(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(10.dp),
                                                verticalArrangement = Arrangement.spacedBy(
                                                    5.dp,
                                                    Alignment.CenterVertically
                                                ),
                                            ) {
                                                CustomText(
                                                    text = stringResource(title),
                                                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                                    fontWeight = MaterialTheme.typography.titleMedium.fontWeight
                                                )
                                                CustomText(
                                                    text = value,
                                                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                                    fontWeight = MaterialTheme.typography.titleMedium.fontWeight
                                                )
                                            }
                                        }
                                    }
                                }

                                Column(
                                    modifier = Modifier.weight(1f),
                                    verticalArrangement = Arrangement.spacedBy(
                                        10.dp,
                                        Alignment.CenterVertically
                                    ),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    column2.forEach { (title, value) ->
                                        CustomClickableCard(
                                            shape = RoundedCornerShape(10)
                                        ) {
                                            Column(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(10.dp),
                                                verticalArrangement = Arrangement.spacedBy(
                                                    5.dp,
                                                    Alignment.CenterVertically
                                                ),
                                            ) {
                                                CustomText(
                                                    text = stringResource(title),
                                                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                                    fontWeight = MaterialTheme.typography.titleMedium.fontWeight
                                                )
                                                CustomText(
                                                    text = value,
                                                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                                    fontWeight = MaterialTheme.typography.titleMedium.fontWeight
                                                )
                                            }
                                        }
                                    }
                                }
                            }

                            HorizontalDivider()

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 20.dp),
                            ) {
                                CustomText(
                                    text = stringResource(R.string.exams),
                                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                                    fontWeight = MaterialTheme.typography.titleLarge.fontWeight
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
