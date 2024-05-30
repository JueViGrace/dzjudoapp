package com.jvg.dzjudoapp.core.modules.home.routes

import cafe.adriel.voyager.navigator.tab.Tab
import com.jvg.dzjudoapp.R
import com.jvg.dzjudoapp.core.modules.profile.presentation.tab.ProfileTab
import com.jvg.dzjudoapp.payment.presentation.navigation.tab.PaymentsTab
import com.jvg.dzjudoapp.student.presentation.navigation.tab.StudentsTab

sealed class HomeTabs(val tab: Tab, val index: UShort, val title: Int, val icon: Int) {
    data object Students : HomeTabs(
        tab = StudentsTab,
        index = 0u,
        title = R.string.students,
        icon = R.drawable.ic_group_24px
    )
    data object Payments : HomeTabs(
        tab = PaymentsTab,
        index = 1u,
        title = R.string.payments,
        icon = R.drawable.ic_payments_24px
    )
    data object Profile : HomeTabs(
        tab = ProfileTab,
        index = 2u,
        title = R.string.profile,
        icon = R.drawable.ic_account_circle_24px
    )
}
