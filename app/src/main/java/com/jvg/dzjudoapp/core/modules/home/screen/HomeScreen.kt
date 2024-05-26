package com.jvg.dzjudoapp.core.modules.home.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabDisposable
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.jvg.dzjudoapp.core.common.Constants.tabs
import com.jvg.dzjudoapp.core.modules.home.routes.HomeTabs
import com.jvg.dzjudoapp.core.presentation.components.BottomNavigationItem

class HomeScreen : Screen {
    override val key: ScreenKey = super.key + uniqueScreenKey

    @Composable
    override fun Content() {
        TabNavigator(
            tab = HomeTabs.Dashboard.tab,
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
                        top = BottomAppBarDefaults.windowInsets.asPaddingValues().calculateTopPadding(),
                        bottom = BottomAppBarDefaults.windowInsets.asPaddingValues().calculateBottomPadding(),
                    ),
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
