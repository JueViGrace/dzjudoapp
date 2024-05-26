package com.jvg.dzjudoapp.core.modules.home.routes

import cafe.adriel.voyager.navigator.tab.Tab
import com.jvg.dzjudoapp.R
import com.jvg.dzjudoapp.payment.presentation.navigation.tab.PaymentsTab
import com.jvg.dzjudoapp.student.presentation.tab.DashboardTab

sealed class HomeTabs(val tab: Tab, val index: UShort, val title: Int, val icon: Int) {
    data object Dashboard : HomeTabs(
        tab = DashboardTab,
        index = 0u,
        title = R.string.home,
        icon = R.drawable.ic_home_app_logo_24px
    )
    data object Payments : HomeTabs(
        tab = PaymentsTab,
        index = 1u,
        title = R.string.payments,
        icon = R.drawable.ic_payments_24px
    )
}