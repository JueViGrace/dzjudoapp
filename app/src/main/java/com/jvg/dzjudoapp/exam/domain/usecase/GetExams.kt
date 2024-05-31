package com.jvg.dzjudoapp.exam.domain.usecase

import com.jvg.dzjudoapp.R
import com.jvg.dzjudoapp.core.common.log
import com.jvg.dzjudoapp.core.state.RequestState
import com.jvg.dzjudoapp.exam.domain.model.Exam
import com.jvg.dzjudoapp.exam.domain.repository.ExamRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

class GetExams(
    private val examRepository: ExamRepository,
    private val coroutineContext: CoroutineContext
) {
    operator fun invoke(): Flow<RequestState<List<Exam>>> = flow {
        emit(RequestState.Loading)

        examRepository.getExams
            .catch { e ->
                emit(
                    RequestState.Error(
                        message = R.string.an_unexpected_error_occurred
                    )
                )
                e.log("GET EXAMS USE CASE")
            }
            .collect { result ->
                when (result) {
                    is RequestState.Error -> {
                        emit(
                            RequestState.Error(
                                message = result.message
                            )
                        )
                    }
                    is RequestState.Success -> {
                        emit(
                            RequestState.Success(
                                data = result.data
                            )
                        )
                    }
                    else -> emit(RequestState.Loading)
                }
            }
    }.flowOn(coroutineContext)
}
