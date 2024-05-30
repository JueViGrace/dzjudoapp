package com.jvg.dzjudoapp.core.modules.home.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabDisposable
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.jvg.dzjudoapp.R
import com.jvg.dzjudoapp.core.common.Constants.tabs
import com.jvg.dzjudoapp.core.modules.home.routes.HomeTabs
import com.jvg.dzjudoapp.core.presentation.components.BottomNavigationItem
import com.jvg.dzjudoapp.core.presentation.components.CustomText
import com.jvg.dzjudoapp.student.presentation.navigation.screens.AddStudentScreen

class HomeScreen : Screen {
    override val key: ScreenKey = super.key + uniqueScreenKey

    @Composable
    override fun Content() {
        TabNavigator(
            tab = HomeTabs.Students.tab,
            tabDisposable = { tabNavigator: TabNavigator ->
                TabDisposable(navigator = tabNavigator, tabs = tabs)
            }
        ) { _ ->
            HomeContent(
                currentScreen = {
                    CurrentTab()
                }
            )
        }
    }

    @Composable
    private fun HomeContent(
        currentScreen: @Composable () -> Unit,
    ) {
        val navigator = LocalNavigator.currentOrThrow

        var visible by remember {
            mutableStateOf(false)
        }

        Scaffold(
            bottomBar = {
                BottomAppBar(
                    actions = {
                        tabs.forEach { tab ->
                            BottomNavigationItem(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .weight(1f)
                                    .padding(horizontal = 5.dp),
                                tab = tab,
                            )
                        }
                    },
                    contentPadding = PaddingValues(
                        top = BottomAppBarDefaults.windowInsets.asPaddingValues()
                            .calculateTopPadding(),
                        bottom = BottomAppBarDefaults.windowInsets.asPaddingValues()
                            .calculateBottomPadding(),
                    ),
                    floatingActionButton = {
                        DropdownMenu(
                            expanded = visible,
                            onDismissRequest = { visible = false }
                        ) {
                            DropdownMenuItem(
                                text = {
                                    CustomText(text = stringResource(R.string.add_student))
                                },
                                onClick = {
                                    visible = false
                                    navigator.push(AddStudentScreen())
                                }
                            )
                            DropdownMenuItem(
                                text = {
                                    CustomText(text = stringResource(R.string.add_payment))
                                },
                                onClick = {
                                    visible = false
                                }
                            )
                        }

                        FloatingActionButton(
                            onClick = {
                                visible = !visible
                            },
                            elevation = FloatingActionButtonDefaults.loweredElevation()
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_edit_24px),
                                contentDescription = "Edit"
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = innerPadding.calculateTopPadding(),
                        bottom = innerPadding.calculateBottomPadding()
                    )
            ) {
                currentScreen()
            }
        }
    }
}
