package com.jvg.dzjudoapp.student.data.repository

import com.jvg.dzjudoapp.R
import com.jvg.dzjudoapp.core.common.Constants.DB_ERROR_MESSAGE
import com.jvg.dzjudoapp.core.common.log
import com.jvg.dzjudoapp.core.state.RequestState
import com.jvg.dzjudoapp.student.data.mappers.toDatabase
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
                        message = DB_ERROR_MESSAGE
                    )
                )
                e.log(tag = "STUDENT REPOSITORY: getStudents")
            }
            .collect { cachedList ->

                emit(
                    RequestState.Success(
                        data = cachedList.map { studentEntity ->
                            studentEntity.toDomain()
                        }
                    )
                )
            }
    }.flowOn(coroutineContext)

    override fun getStudent(id: String): Flow<RequestState<Student>> = flow<RequestState<Student>> {
        emit(RequestState.Loading)

        studentDataSource.studentLocal.getStudent(id)
            .catch { e ->
                emit(
                    RequestState.Error(
                        message = DB_ERROR_MESSAGE
                    )
                )
            }
            .collect { studentEntity ->
                if (studentEntity != null) {
                    emit(
                        RequestState.Success(
                            data = studentEntity.toDomain()
                        )
                    )
                } else {
                    emit(
                        RequestState.Error(
                            message = R.string.this_student_doesn_t_exists
                        )
                    )
                }
            }
    }.flowOn(coroutineContext)

    override suspend fun addStudent(student: Student) {
        studentDataSource.studentLocal.addStudent(student.toDatabase())
    }

    override suspend fun activeStudents(active: Boolean, id: String) {
        studentDataSource.studentLocal.activeStudent(active, id)
    }

    override suspend fun deleteStudent(id: String) {
        studentDataSource.studentLocal.deleteStudent(id)
    }
}
