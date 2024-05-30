package com.jvg.dzjudoapp.core.common

import com.jvg.dzjudoapp.R
import com.jvg.dzjudoapp.core.modules.home.routes.HomeTabs
import kotlinx.coroutines.flow.SharingStarted

object Constants {

    private const val STOP_TIME_OUT: Long = 5000L

    val SHARING_STARTED: SharingStarted = SharingStarted.WhileSubscribed(STOP_TIME_OUT)

    val DB_ERROR_MESSAGE = R.string.database_not_available

    const val PHONE_LENGTH = 10

    val tabs = listOf(
        HomeTabs.Students.tab,
        HomeTabs.Payments.tab,
        HomeTabs.Profile.tab,
    )
}
