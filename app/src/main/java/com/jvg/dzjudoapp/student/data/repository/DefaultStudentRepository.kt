package com.jvg.dzjudoapp.student.data.repository

import com.jvg.dzjudoapp.R
import com.jvg.dzjudoapp.core.common.log
import com.jvg.dzjudoapp.core.state.RequestState
import com.jvg.dzjudoapp.student.data.mappers.toDomain
import com.jvg.dzjudoapp.student.data.source.StudentDataSource
import com.jvg.dzjudoapp.student.domain.model.Student
import com.jvg.dzjudoapp.student.domain.repository.StudentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

class DefaultStudentRepository(
    override val studentDataSource: StudentDataSource,
    override val coroutineContext: CoroutineContext,
) : StudentRepository {
    override fun getStudents(): Flow<RequestState<List<Student>>> = flow {
        emit(RequestState.Loading)

        studentDataSource.studentLocal
            .getStudents()
            .catch { e ->
                emit(
                    RequestState.Error(
                        message = R.string.database_not_available
                    )
                )
                e.log(tag = "STUDENT REPOSITORY: getStudents")
            }
            .collect { cachedList ->
                if (cachedList.isNotEmpty()) {
                    emit(
                        RequestState.Success(
                            data = cachedList.map { studentEntity ->
                                studentEntity.toDomain()
                            }
                        )
                    )
                } else {
                    emit(
                        RequestState.Error(
                            message = R.string.there_are_no_students
                        )
                    )
                }
            }
    }.flowOn(coroutineContext)

    override fun getStudent(id: String): Flow<RequestState<Student>> {
        TODO("Not yet implemented")
    }

    override suspend fun addStudent(student: Student) {
        TODO("Not yet implemented")
    }

    override suspend fun activeStudents(active: String, id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteStudent(id: String) {
        TODO("Not yet implemented")
    }
}
