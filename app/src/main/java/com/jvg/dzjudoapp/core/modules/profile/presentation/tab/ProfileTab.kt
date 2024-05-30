package com.jvg.dzjudoapp.core.modules.profile.presentation.tab

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.jvg.dzjudoapp.R
import com.jvg.dzjudoapp.core.modules.home.routes.HomeTabs
import com.jvg.dzjudoapp.core.modules.profile.presentation.viewmodel.ProfileViewModel
import com.jvg.dzjudoapp.core.presentation.components.CustomText
import com.jvg.dzjudoapp.core.presentation.components.DefaultStateLayoutComponent
import com.jvg.dzjudoapp.core.presentation.components.ErrorScreen
import org.koin.androidx.compose.koinViewModel

object ProfileTab : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(id = HomeTabs.Profile.title)
            val icon = painterResource(id = HomeTabs.Profile.icon)
            return TabOptions(
                title = title,
                index = HomeTabs.Profile.index,
                icon = icon
            )
        }

    @Composable
    override fun Content() {
        val viewModel = koinViewModel<ProfileViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()

        DefaultStateLayoutComponent(
            state = state.students
        ) { list ->
            if (list.isNotEmpty()) {
                LazyColumn {
                    items(list) { student ->
                        CustomText(text = student.id)
                    }
                }
            } else {
                ErrorScreen(
                    message = R.string.there_are_no_students
                )
            }
        }
    }
}
