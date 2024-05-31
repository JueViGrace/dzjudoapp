package com.jvg.dzjudoapp.exam.data.local

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOneOrNull
import com.jvg.dzjudoapp.Exam
import com.jvg.dzjudoapp.core.database.helper.DbHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow

class DefaultExamLocal(
    private val dbHelper: DbHelper,
    private val scope: CoroutineScope
) : ExamLocal {
    override suspend fun getExams(): Flow<List<Exam>> = scope.async {
        dbHelper.withDatabase { db ->
            db.examQueries
                .getExams()
                .asFlow()
                .mapToList(scope.coroutineContext)
        }
    }.await()

    override suspend fun getExam(id: String): Flow<Exam?> = scope.async {
        dbHelper.withDatabase { db ->
            db.examQueries
                .getExams()
                .asFlow()
                .mapToOneOrNull(scope.coroutineContext)
        }
    }.await()

    override suspend fun addExam(exam: Exam) {
        scope.async {
            dbHelper.withDatabase { db ->
                db.examQueries.addExam(exam)
            }
        }.await()
    }

    override suspend fun deleteExam(id: String) {
        scope.async {
            dbHelper.withDatabase { db ->
                db.examQueries.deleteExam(id)
            }
        }.await()
    }
}
