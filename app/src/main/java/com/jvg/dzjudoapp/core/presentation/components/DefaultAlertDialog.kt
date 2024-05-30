package com.jvg.dzjudoapp.core.presentation.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.jvg.dzjudoapp.R

@Composable
fun DefaultAlertDialog(
    onVisibleChange: (Boolean) -> Unit,
    onCancel: () -> Unit,
    onConfirm: () -> Unit,
    icon: Int? = null,
    title: String? = null,
    text: String? = null
) {
    AlertDialog(
        onDismissRequest = { onVisibleChange(false) },
        dismissButton = {
            ElevatedButton(
                onClick = {
                    onVisibleChange(false)
                    onCancel()
                },
                colors = ButtonDefaults.elevatedButtonColors().copy(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                )
            ) {
                CustomText(text = stringResource(R.string.cancel))
            }
        },
        confirmButton = {
            ElevatedButton(
                onClick = {
                    onVisibleChange(false)
                    onConfirm()
                },
                colors = ButtonDefaults.elevatedButtonColors().copy(
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                    contentColor = MaterialTheme.colorScheme.onErrorContainer
                )
            ) {
                CustomText(text = stringResource(id = R.string.accept))
            }
        },
        icon = if (icon != null) {
            { Icon(painter = painterResource(id = icon), contentDescription = null) }
        } else {
            null
        },
        title = if (title != null) {
            {
                CustomText(
                    text = title,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    fontWeight = MaterialTheme.typography.titleLarge.fontWeight
                )
            }
        } else {
            null
        },
        text = if (text != null) {
            { CustomText(text = text) }
        } else {
            null
        }
    )
}
