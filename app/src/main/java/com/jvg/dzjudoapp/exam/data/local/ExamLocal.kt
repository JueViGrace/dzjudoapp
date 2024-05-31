package com.jvg.dzjudoapp.exam.data.local

import com.jvg.dzjudoapp.Exam
import kotlinx.coroutines.flow.Flow

interface ExamLocal {
    suspend fun getExams(): Flow<List<Exam>>

    suspend fun getExam(id: String): Flow<Exam?>

    suspend fun addExam(exam: Exam)

    suspend fun deleteExam(id: String)
}
