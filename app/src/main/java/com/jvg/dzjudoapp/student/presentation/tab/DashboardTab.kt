package com.jvg.dzjudoapp.student.presentation.tab

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.jvg.dzjudoapp.core.modules.home.routes.HomeTabs
import com.jvg.dzjudoapp.core.presentation.components.CustomText
import com.jvg.dzjudoapp.core.presentation.components.ErrorScreen
import com.jvg.dzjudoapp.core.presentation.components.LoadingScreen
import com.jvg.dzjudoapp.student.presentation.viewmodel.StudentsViewModel
import org.koin.androidx.compose.koinViewModel

object DashboardTab : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(id = HomeTabs.Dashboard.title)
            val icon = painterResource(id = HomeTabs.Dashboard.icon)
            return TabOptions(
                title = title,
                index = HomeTabs.Dashboard.index,
                icon = icon
            )
        }

    @Composable
    override fun Content() {
        val viewModel = koinViewModel<StudentsViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()

        Scaffold { innerPadding ->
            state.students.DisplayResult(
                onLoading = {
                    LoadingScreen()
                },
                onError = {
                    ErrorScreen(it)
                },
                onSuccess = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        CustomText(text = "Dashboard")
                    }
                },
            )
        }
    }
}
