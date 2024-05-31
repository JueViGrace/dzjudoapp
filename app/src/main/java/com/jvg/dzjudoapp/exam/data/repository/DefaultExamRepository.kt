package com.jvg.dzjudoapp.exam.data.repository

import com.jvg.dzjudoapp.R
import com.jvg.dzjudoapp.core.common.Constants.DB_ERROR_MESSAGE
import com.jvg.dzjudoapp.core.common.log
import com.jvg.dzjudoapp.core.state.RequestState
import com.jvg.dzjudoapp.exam.data.mappers.toDatabase
import com.jvg.dzjudoapp.exam.data.mappers.toDomain
import com.jvg.dzjudoapp.exam.data.source.ExamDataSource
import com.jvg.dzjudoapp.exam.domain.model.Exam
import com.jvg.dzjudoapp.exam.domain.repository.ExamRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

class DefaultExamRepository(
    override val examDataSource: ExamDataSource,
    override val coroutineContext: CoroutineContext
) : ExamRepository {
    override val getExams: Flow<RequestState<List<Exam>>> = flow {
        emit(RequestState.Loading)

        examDataSource.examLocal
            .getExams()
            .catch { e ->
                emit(
                    RequestState.Error(
                        message = DB_ERROR_MESSAGE
                    )
                )
                e.log("EXAM REPOSITORY: getExams")
            }
            .collect { list ->
                emit(
                    RequestState.Success(
                        data = list.map { examEntity ->
                            examEntity.toDomain()
                        }
                    )
                )
            }
    }.flowOn(coroutineContext)

    override fun getExam(id: String): Flow<RequestState<Exam>> = flow {
        emit(RequestState.Loading)

        examDataSource.examLocal
            .getExam(id)
            .catch { e ->
                emit(
                    RequestState.Error(
                        message = DB_ERROR_MESSAGE
                    )
                )
                e.log("EXAM REPOSITORY: getExams")
            }
            .collect { list ->
                if (list != null) {
                    emit(
                        RequestState.Success(
                            data = list.toDomain()
                        )
                    )
                } else {
                    emit(
                        RequestState.Error(
                            message = R.string.this_exam_doesn_t_exists
                        )
                    )
                }
            }
    }.flowOn(coroutineContext)

    override suspend fun addExams(exam: Exam) {
        examDataSource.examLocal.addExam(exam.toDatabase())
    }

    override suspend fun deleteExam(id: String) {
        examDataSource.examLocal.deleteExam(id)
    }
}
