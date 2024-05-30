package com.jvg.dzjudoapp.student.di

import com.jvg.dzjudoapp.student.data.local.DefaultStudentLocal
import com.jvg.dzjudoapp.student.data.local.StudentLocal
import com.jvg.dzjudoapp.student.data.repository.DefaultStudentRepository
import com.jvg.dzjudoapp.student.data.source.DefaultStudentDataSource
import com.jvg.dzjudoapp.student.data.source.StudentDataSource
import com.jvg.dzjudoapp.student.domain.repository.StudentRepository
import com.jvg.dzjudoapp.student.domain.usecase.ActiveStudent
import com.jvg.dzjudoapp.student.domain.usecase.AddStudent
import com.jvg.dzjudoapp.student.domain.usecase.DeleteStudent
import com.jvg.dzjudoapp.student.domain.usecase.GetStudent
import com.jvg.dzjudoapp.student.domain.usecase.GetStudents
import com.jvg.dzjudoapp.student.domain.usecase.ValidateStudent
import com.jvg.dzjudoapp.student.presentation.viewmodel.AddStudentViewModel
import com.jvg.dzjudoapp.student.presentation.viewmodel.StudentDetailsViewModel
import com.jvg.dzjudoapp.student.presentation.viewmodel.StudentsViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val studentModule = module {
    singleOf(::DefaultStudentLocal) bind StudentLocal::class

    singleOf(::DefaultStudentDataSource) bind StudentDataSource::class

    singleOf(::DefaultStudentRepository) bind StudentRepository::class

    singleOf(::GetStudents)

    singleOf(::AddStudent)

    singleOf(::GetStudent)

    singleOf(::ActiveStudent)

    singleOf(::DeleteStudent)

    singleOf(::ValidateStudent)

    factoryOf(::StudentsViewModel)

    factoryOf(::AddStudentViewModel)

    factoryOf(::StudentDetailsViewModel)
}
