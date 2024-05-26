package com.jvg.dzjudoapp.student.data.local

import kotlinx.coroutines.flow.Flow
import com.jvg.dzjudoapp.Student as StudentEntity

interface StudentLocal {
    suspend fun getStudents(): Flow<List<StudentEntity>>

    suspend fun getStudent(id: String): Flow<StudentEntity?>

    suspend fun addStudent(student: StudentEntity)

    suspend fun activeStudent(active: Boolean, id: String)

    suspend fun deleteStudent(id: String)
}
