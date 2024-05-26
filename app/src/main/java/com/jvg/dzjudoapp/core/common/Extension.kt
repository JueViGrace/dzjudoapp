package com.jvg.dzjudoapp.core.common

import android.util.Log

fun Throwable.log(tag: String) {
    Log.e(
        tag,
        """
            Message: ${this.message}\n
            Localized Message: ${this.localizedMessage}
        """.trimIndent(),
        this
    )
}
