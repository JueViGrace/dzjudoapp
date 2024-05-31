package com.jvg.dzjudoapp.exam.domain.repository

import com.jvg.dzjudoapp.core.state.RequestState
import com.jvg.dzjudoapp.exam.data.source.ExamDataSource
import com.jvg.dzjudoapp.exam.domain.model.Exam
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext

interface ExamRepository {
    val examDataSource: ExamDataSource
    val coroutineContext: CoroutineContext

    val getExams: Flow<RequestState<List<Exam>>>

    fun getExam(id: String): Flow<RequestState<Exam>>

    suspend fun addExams(exam: Exam)

    suspend fun deleteExam(id: String)
}
