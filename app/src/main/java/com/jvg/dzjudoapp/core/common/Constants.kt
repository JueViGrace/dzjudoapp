package com.jvg.dzjudoapp.core.common

import com.jvg.dzjudoapp.core.modules.home.routes.HomeTabs
import kotlinx.coroutines.flow.SharingStarted

object Constants {

    private const val STOP_TIME_OUT: Long = 5000L

    val SHARING_STARTED: SharingStarted = SharingStarted.WhileSubscribed(STOP_TIME_OUT)

    val tabs = listOf(
        HomeTabs.Dashboard.tab,
        HomeTabs.Payments.tab,
    )
}
