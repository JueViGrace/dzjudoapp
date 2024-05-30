package com.jvg.dzjudoapp.student.presentation.navigation.tab

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.jvg.dzjudoapp.R
import com.jvg.dzjudoapp.core.modules.home.routes.HomeTabs
import com.jvg.dzjudoapp.core.presentation.components.CustomClickableCard
import com.jvg.dzjudoapp.core.presentation.components.CustomText
import com.jvg.dzjudoapp.core.presentation.components.DefaultAlertDialog
import com.jvg.dzjudoapp.core.presentation.components.DefaultBackArrow
import com.jvg.dzjudoapp.core.presentation.components.DefaultStateLayoutComponent
import com.jvg.dzjudoapp.core.presentation.components.DefaultTopBar
import com.jvg.dzjudoapp.core.presentation.components.ErrorScreen
import com.jvg.dzjudoapp.core.presentation.components.LetterIcon
import com.jvg.dzjudoapp.core.presentation.components.ListFooter
import com.jvg.dzjudoapp.core.presentation.components.ListStickyHeader
import com.jvg.dzjudoapp.core.presentation.components.SearchBarComponent
import com.jvg.dzjudoapp.student.domain.model.Student
import com.jvg.dzjudoapp.student.presentation.navigation.screens.StudentDetailsScreen
import com.jvg.dzjudoapp.student.presentation.viewmodel.StudentsViewModel
import org.koin.androidx.compose.koinViewModel

object StudentsTab : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(id = HomeTabs.Students.title)
            val icon = painterResource(id = HomeTabs.Students.icon)
            return TabOptions(
                title = title,
                index = HomeTabs.Students.index,
                icon = icon
            )
        }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = koinViewModel<StudentsViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()
        val tabOptions = LocalTabNavigator.current.current.options

        var actionsVisible by remember {
            mutableStateOf(false)
        }

        val focus = LocalFocusManager.current

        DefaultStateLayoutComponent(
            topBar = {
                AnimatedVisibility(
                    visible = !state.searchBarVisible,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    DefaultTopBar(
                        title = {
                            CustomText(
                                text = tabOptions.title,
                                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                                fontWeight = MaterialTheme.typography.titleLarge.fontWeight
                            )
                        },
                        actions = {
                            IconButton(
                                onClick = {
                                    actionsVisible = !actionsVisible
                                }
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_more_vert_24px),
                                    contentDescription = "More options"
                                )
                            }

                            DropdownMenu(
                                expanded = actionsVisible,
                                onDismissRequest = { actionsVisible = false }
                            ) {
                                DropdownMenuItem(
                                    leadingIcon = {
                                        Icon(
                                            modifier = Modifier.size(18.dp),
                                            painter = painterResource(id = R.drawable.ic_search_24px),
                                            contentDescription = "Search"
                                        )
                                    },
                                    text = {
                                        CustomText(
                                            text = "Search",
                                            fontSize = MaterialTheme.typography.bodyMedium.fontSize
                                        )
                                    },
                                    onClick = {
                                        viewModel.toggleVisibility(true)
                                        actionsVisible = false
                                    }
                                )
                            }
                        }
                    )
                }

                AnimatedVisibility(
                    visible = state.searchBarVisible,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    DefaultTopBar(
                        title = {
                            SearchBarComponent(
                                query = state.searchText,
                                onQueryChange = viewModel::onSearchTextChange,
                                onSearch = {
                                    focus.clearFocus()
                                    viewModel.onSearchTextChange(it)
                                }
                            )
                        },
                        navigationIcon = {
                            DefaultBackArrow {
                                viewModel.onSearchTextChange("")
                                viewModel.toggleVisibility(false)
                            }
                        },
                    )
                }
            },
            state = state.students
        ) { students ->
            if (students.isNotEmpty()) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    students.groupBy {
                        it.name.first()
                    }.forEach { (initial, list) ->

                        stickyHeader {
                            ListStickyHeader(text = initial.toString())
                        }

                        items(list) { student ->
                            StudentListContent(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 10.dp),
                                student = student,
                                onSelect = { id ->
                                    navigator.parent?.push(StudentDetailsScreen(id))
                                },
                                onActive = { active, id ->
                                    viewModel.onActive(active, id)
                                },
                                onDelete = { id ->
                                    viewModel.onDelete(id)
                                }
                            )
                        }
                    }

                    item {
                        ListFooter(
                            text = stringResource(R.string.end_of_list)
                        )
                    }
                }
            } else {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    ErrorScreen(
                        message = R.string.there_are_no_students
                    )
                }
            }
        }
    }

    @Composable
    private fun StudentListContent(
        modifier: Modifier = Modifier,
        student: Student,
        onSelect: (String) -> Unit,
        onActive: (Boolean, String) -> Unit,
        onDelete: (String) -> Unit
    ) {
        var showDialog by remember {
            mutableStateOf(false)
        }

        AnimatedVisibility(visible = showDialog) {
            DefaultAlertDialog(
                title = "${stringResource(R.string.delete)} ${student.name} ${student.lastname}?",
                text = "${
                    stringResource(R.string.are_you_sure_you_want_to_delete)
                } ${student.name} ${student.lastname}?",
                icon = R.drawable.ic_delete_24px,
                onVisibleChange = { newValue ->
                    showDialog = newValue
                },
                onConfirm = {
                    onDelete(student.id)
                },
                onCancel = {
                    showDialog = false
                }
            )
        }

        Column(
            modifier = modifier.clickable {
                onSelect(student.id)
            },
            verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    LetterIcon(letter = student.name.first().toString())
                    CustomText(text = "${student.name} ${student.lastname}")
                }
                Checkbox(
                    checked = student.active,
                    onCheckedChange = { newValue ->
                        onActive(newValue, student.id)
                    }
                )
            }

            if (!student.active) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CustomClickableCard(
                        modifier = Modifier.weight(1f),
                        colors = CardDefaults.elevatedCardColors().copy(
                            containerColor = MaterialTheme.colorScheme.error,
                            contentColor = MaterialTheme.colorScheme.onError
                        ),
                        shape = RoundedCornerShape(20)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(5.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CustomText(
                                text = stringResource(R.string.inactive_student),
                                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                fontWeight = MaterialTheme.typography.titleMedium.fontWeight
                            )
                        }
                    }
                    IconButton(
                        onClick = { showDialog = true }
                    ) {
                        Icon(
                            tint = MaterialTheme.colorScheme.error,
                            painter = painterResource(id = R.drawable.ic_delete_24px),
                            contentDescription = "Delete student"
                        )
                    }
                }
            }
        }
    }
}
