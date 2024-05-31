package com.jvg.dzjudoapp.core.presentation.actions

import com.jvg.dzjudoapp.R

sealed class TopBarActions(
    val title: Int,
    val icon: Int? = null
) {
    data object Search : TopBarActions(
        title = R.string.search,
        icon = R.drawable.ic_search_24px
    )

    data object Edit : TopBarActions(
        title = R.string.edit,
        icon = R.drawable.ic_edit_24px
    )
}
