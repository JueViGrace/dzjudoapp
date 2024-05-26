package com.jvg.dzjudoapp.core.modules.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.LocalLifecycleOwner
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.FadeTransition
import com.jvg.dzjudoapp.core.modules.app.navigation.AppScreen
import com.jvg.dzjudoapp.core.modules.home.screen.HomeScreen
import com.jvg.dzjudoapp.core.presentation.theme.DZTheme

@Composable
fun App() {
    DZTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            CompositionLocalProvider(
                LocalLifecycleOwner provides androidx.compose.ui.platform.LocalLifecycleOwner.current
            ) {
                Navigator(
                    screen = AppScreen(
                        initialScreen = HomeScreen()
                    )
                ) { navigator ->
                    FadeTransition(navigator = navigator)
                }
            }
        }
    }
}
