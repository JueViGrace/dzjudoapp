package com.jvg.dzjudoapp.student.presentation.navigation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.jvg.dzjudoapp.core.presentation.components.CustomClickableCard
import com.jvg.dzjudoapp.core.presentation.components.CustomText
import com.jvg.dzjudoapp.core.presentation.components.DefaultBackArrow
import com.jvg.dzjudoapp.core.presentation.components.DefaultStateLayoutComponent
import com.jvg.dzjudoapp.core.presentation.components.DefaultTopBar
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
                    }
                )
            },
            state = state.student
        ) { student: Student ->
            title = "${student.name} ${student.lastname}"
            val column1 =
                mapOf(
                    Pair(R.string.name, student.name),
                    Pair(R.string.name, student.name),
                    Pair(R.string.name, student.name),
                )

            val column2 = mapOf(
                Pair(R.string.name, student.name),
                Pair(R.string.name, student.name),
                Pair(R.string.name, student.name),
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .defaultMinSize(minHeight = 100.dp)
                    )
                }

                item {
                    CustomClickableCard(
                        shape = RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp)
                    ) {
                        Column {
                            column1.forEach { (title, value) ->
                                CustomClickableCard(
                                    shape = RoundedCornerShape(20)
                                ) {
                                    Column(
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        CustomText(text = stringResource(title))
                                        CustomText(text = value)
                                    }
                                }
                            }
                        }

                        Column {
                            column2.forEach { (title, value) ->
                                CustomClickableCard(
                                    shape = RoundedCornerShape(20)
                                ) {
                                    Column(
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        CustomText(text = stringResource(title))
                                        CustomText(text = value)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
