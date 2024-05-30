package com.jvg.dzjudoapp.core.presentation.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun TextFieldComponent(
    modifier: Modifier = Modifier,
    value: String,
    newValue: (String) -> Unit,
    label: Int? = null,
    placeholder: Int? = null,
    supportingText: Int? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(
        imeAction = ImeAction.Done,
        keyboardType = KeyboardType.Text
    ),
    icon: Int? = null,
    errorStatus: Boolean = false,
    readOnly: Boolean = false,
    enabled: Boolean = true
) {
    val focus = LocalFocusManager.current
    CustomOutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChanged = {
            newValue(it)
        },
        label = if (label != null){
            {
                CustomText(text = stringResource(label))
            }
        } else {
               null
               },
        readOnly = readOnly,
        enabled = enabled,
        keyboardOptions = keyboardOptions,
        placeholder = if (placeholder != null){
            {
                CustomText(text = stringResource(placeholder))
            }
        } else {
               null
               },
        supportingText = if (supportingText != null) {
            { CustomText(text = stringResource(supportingText)) }
        } else {
            null
        },
        keyboardActions = KeyboardActions(
            onNext = {
                focus.moveFocus(FocusDirection.Down)
            }
        ),
        leadingIcon =
        if (icon != null) {
            { Icon(painter = painterResource(icon), contentDescription = null) }
        } else {
            null
        },
        isError = errorStatus
    )
}
