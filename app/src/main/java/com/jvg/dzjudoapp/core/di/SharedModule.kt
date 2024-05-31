package com.jvg.dzjudoapp.core.di

import com.jvg.dzjudoapp.exam.di.examModule
import com.jvg.dzjudoapp.payment.di.paymentModule
import com.jvg.dzjudoapp.student.di.studentModule
import org.koin.dsl.module

val sharedModule = module {
    includes(
        paymentModule,
        studentModule,
        examModule
    )
}
