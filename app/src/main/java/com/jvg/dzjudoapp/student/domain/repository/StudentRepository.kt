package com.jvg.dzjudoapp.student.domain.repository

import com.jvg.dzjudoapp.core.state.RequestState
import com.jvg.dzjudoapp.student.data.source.StudentDataSource
import com.jvg.dzjudoapp.student.domain.model.Student
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext

interface StudentRepository {
    val studentDataSource: StudentDataSource
    val coroutineContext: CoroutineContext

    fun getStudents(): Flow<RequestState<List<Student>>>

    fun getStudent(id: String): Flow<RequestState<Student>>

    suspend fun addStudent(student: Student)

    suspend fun activeStudents(active: Boolean, id: String)

    suspend fun deleteStudent(id: String)
}
