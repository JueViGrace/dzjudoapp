package com.jvg.dzjudoapp.core.modules.profile.di

import com.jvg.dzjudoapp.core.modules.profile.presentation.viewmodel.ProfileViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val profileModule = module {

    factoryOf(::ProfileViewModel)
}
