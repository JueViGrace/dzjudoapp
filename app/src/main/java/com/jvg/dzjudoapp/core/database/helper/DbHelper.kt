package com.jvg.dzjudoapp.core.database.helper

import com.jvg.dzjudoapp.DZJudoDb
import com.jvg.dzjudoapp.core.database.driver.DriverFactory
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class DbHelper(private val driverFactory: DriverFactory) {

    private var db: DZJudoDb? = null

    private val mutex = Mutex()

    suspend fun <Result : Any?> withDatabase(block: suspend (DZJudoDb) -> Result): Result = mutex.withLock {
        if (db == null) {
            db = createDb(driverFactory)
        }

        return@withLock block(db!!)
    }

    private fun createDb(driverFactory: DriverFactory): DZJudoDb {
        return DZJudoDb(driver = driverFactory.createDriver())
    }
}
