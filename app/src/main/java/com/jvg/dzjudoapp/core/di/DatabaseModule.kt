package com.jvg.dzjudoapp.core.di

import com.jvg.dzjudoapp.core.database.driver.DriverFactory
import com.jvg.dzjudoapp.core.database.helper.DbHelper
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val databaseModule = module {
    singleOf(::DriverFactory)

    singleOf(::DbHelper)
}
