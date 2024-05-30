package com.jvg.dzjudoapp.student.presentation.navigation.tab

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.jvg.dzjudoapp.R
import com.jvg.dzjudoapp.core.modules.home.routes.HomeTabs
import com.jvg.dzjudoapp.core.presentation.components.CustomText
import com.jvg.dzjudoapp.core.presentation.components.DefaultBackArrow
import com.jvg.dzjudoapp.core.presentation.components.DefaultStateLayoutComponent
import com.jvg.dzjudoapp.core.presentation.components.DefaultTopBar
import com.jvg.dzjudoapp.core.presentation.components.ErrorScreen
import com.jvg.dzjudoapp.core.presentation.components.SearchBarComponent
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

    @Composable
    override fun Content() {
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
                    items(students) { student ->
                        CustomText(text = student.id)
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
}
