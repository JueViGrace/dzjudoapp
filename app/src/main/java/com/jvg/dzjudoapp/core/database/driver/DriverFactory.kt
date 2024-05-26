package com.jvg.dzjudoapp.core.database.driver

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.jvg.dzjudoapp.DZJudoDb

class DriverFactory(private val context: Context) {
    fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            schema = DZJudoDb.Schema,
            context = context,
            "dzjudo.db",
        )
    }
}
