package com.jvg.dzjudoapp.core.presentation.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.jvg.dzjudoapp.R

@Composable
fun DefaultBackArrow(
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_back_24px),
            contentDescription = "On back"
        )
    }
}
