package com.jvg.dzjudoapp.exam.di

import com.jvg.dzjudoapp.exam.data.local.DefaultExamLocal
import com.jvg.dzjudoapp.exam.data.local.ExamLocal
import com.jvg.dzjudoapp.exam.data.repository.DefaultExamRepository
import com.jvg.dzjudoapp.exam.data.source.DefaultExamDataSource
import com.jvg.dzjudoapp.exam.data.source.ExamDataSource
import com.jvg.dzjudoapp.exam.domain.repository.ExamRepository
import com.jvg.dzjudoapp.exam.domain.usecase.AddExam
import com.jvg.dzjudoapp.exam.domain.usecase.GetExams
import com.jvg.dzjudoapp.exam.domain.usecase.ValidateExam
import com.jvg.dzjudoapp.exam.presentation.viewmodel.ExamsViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val examModule = module {
    singleOf(::DefaultExamLocal) bind ExamLocal::class

    singleOf(::DefaultExamDataSource) bind ExamDataSource::class

    singleOf(::DefaultExamRepository) bind ExamRepository::class

    singleOf(::AddExam)

    singleOf(::ValidateExam)

    singleOf(::GetExams)

    factoryOf(::ExamsViewModel)
}
