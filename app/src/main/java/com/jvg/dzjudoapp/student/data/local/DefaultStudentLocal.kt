package com.jvg.dzjudoapp.student.data.local

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOneOrNull
import com.jvg.dzjudoapp.Student
import com.jvg.dzjudoapp.core.database.helper.DbHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow

class DefaultStudentLocal(
    private val dbHelper: DbHelper,
    private val scope: CoroutineScope
) : StudentLocal {
    override suspend fun getStudents(): Flow<List<Student>> = scope.async {
        dbHelper.withDatabase { db ->
            db.studentQueries
                .getStudents()
                .asFlow()
                .mapToList(scope.coroutineContext)
        }
    }.await()

    override suspend fun getStudent(id: String): Flow<Student?> = scope.async {
        dbHelper.withDatabase { db ->
            db.studentQueries
                .getStudent(id)
                .asFlow()
                .mapToOneOrNull(scope.coroutineContext)
        }
    }.await()

    override suspend fun addStudent(student: Student) = scope.async {
        dbHelper.withDatabase { db ->
            db.studentQueries.addStudent(student = student)
        }
    }.await()

    override suspend fun activeStudent(active: Boolean, id: String) = scope.async {
        dbHelper.withDatabase { db ->
            db.studentQueries.activeStudent(active = active, id = id)
        }
    }.await()

    override suspend fun deleteStudent(id: String) = scope.async {
        dbHelper.withDatabase { db ->
            db.studentQueries.deleteStudent(id = id)
        }
    }.await()
}
