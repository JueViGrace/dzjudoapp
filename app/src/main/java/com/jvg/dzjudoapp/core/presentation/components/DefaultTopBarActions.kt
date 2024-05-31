package com.jvg.dzjudoapp.core.presentation.components

import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jvg.dzjudoapp.R
import com.jvg.dzjudoapp.core.presentation.actions.TopBarActions

@Composable
fun DefaultTopBarActions(
    onMenuClick: (TopBarActions) -> Unit,
    items: List<TopBarActions>
) {
    var actionsVisible by remember {
        mutableStateOf(false)
    }

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
        items.forEach { action ->
            DropdownMenuItem(
                leadingIcon = if (action.icon != null) {
                    {
                        Icon(
                            modifier = Modifier.size(18.dp),
                            painter = painterResource(id = action.icon),
                            contentDescription = "Search"
                        )
                    }
                } else {
                    null
                },
                text = {
                    CustomText(
                        text = stringResource(id = action.title),
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        fontWeight = MaterialTheme.typography.bodyMedium.fontWeight
                    )
                },
                onClick = {
                    onMenuClick(action)
                    actionsVisible = false
                }
            )
        }
    }
}
