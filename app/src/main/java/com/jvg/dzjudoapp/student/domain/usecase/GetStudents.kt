package com.jvg.dzjudoapp.student.domain.usecase

import com.jvg.dzjudoapp.R
import com.jvg.dzjudoapp.core.common.log
import com.jvg.dzjudoapp.core.state.RequestState
import com.jvg.dzjudoapp.student.domain.model.Student
import com.jvg.dzjudoapp.student.domain.repository.StudentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

class GetStudents(
    private val studentRepository: StudentRepository,
    private val coroutineContext: CoroutineContext
) {
    operator fun invoke(): Flow<RequestState<List<Student>>> = flow {
        emit(RequestState.Loading)

        studentRepository.getStudents()
            .catch { e ->
                emit(
                    RequestState.Error(
                        message = R.string.an_unexpected_error_occurred
                    )
                )
                e.log("GET STUDENTS USE CASE")
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
