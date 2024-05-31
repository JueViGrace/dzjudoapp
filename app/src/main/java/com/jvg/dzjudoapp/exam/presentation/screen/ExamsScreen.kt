package com.jvg.dzjudoapp.exam.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.jvg.dzjudoapp.core.presentation.components.DefaultStateLayoutComponent
import com.jvg.dzjudoapp.exam.presentation.viewmodel.ExamsViewModel
import org.koin.androidx.compose.koinViewModel

object ExamsScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = koinViewModel<ExamsViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()

        DefaultStateLayoutComponent(
            topBar = {

            },
            state = state.exams
        ) { list ->


        }
    }
}