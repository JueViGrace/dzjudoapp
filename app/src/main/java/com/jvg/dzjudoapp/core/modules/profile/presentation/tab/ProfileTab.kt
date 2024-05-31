package com.jvg.dzjudoapp.core.modules.profile.presentation.tab

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.jvg.dzjudoapp.R
import com.jvg.dzjudoapp.core.modules.home.routes.HomeTabs
import com.jvg.dzjudoapp.core.modules.profile.presentation.viewmodel.ProfileViewModel
import com.jvg.dzjudoapp.core.presentation.components.CustomText
import com.jvg.dzjudoapp.core.presentation.components.DefaultLayoutComponent
import com.jvg.dzjudoapp.core.presentation.components.DefaultTopBar
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
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = koinViewModel<ProfileViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()

        DefaultLayoutComponent(
            topBar = {
                DefaultTopBar(
                    title = {
                        CustomText(
                            text = stringResource(id = R.string.profile),
                            fontSize = MaterialTheme.typography.titleLarge.fontSize,
                            fontWeight = MaterialTheme.typography.titleLarge.fontWeight
                        )
                    }
                )
            }
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier.defaultMinSize(minHeight = 100.dp, minWidth = 100.dp),
                        painter = painterResource(id = R.drawable.ic_account_circle_24px),
                        contentDescription = "Logo"
                    )
                }

                HorizontalDivider()

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 5.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ElevatedButton(
                            onClick = {

                            },
                            contentPadding = PaddingValues(16.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterHorizontally),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_quiz_24px),
                                    contentDescription = "Create exam"
                                )

                                CustomText(text = "Create exam")
                            }
                        }
                    }
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        /*ElevatedButton(
                            onClick = {},
                            contentPadding = PaddingValues(16.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterHorizontally),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    modifier = Modifier.requiredSize(24.dp),
                                    painter = painterResource(id = R.drawable.belt),
                                    contentDescription = "Create belt"
                                )

                                CustomText(
                                    text = "Create belts"
                                )
                            }
                        }*/
                    }
                }
            }
        }
    }
}
