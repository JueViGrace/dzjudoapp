package com.jvg.dzjudoapp.core.modules.app.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition

data class AppScreen(
    val initialScreen: Screen
) : Screen {
    override val key: ScreenKey = super.key + uniqueScreenKey

    @Composable
    override fun Content() {
        Navigator(
            screen = initialScreen
        ) { navigator ->
            SlideTransition(navigator = navigator)
        }
    }
}
