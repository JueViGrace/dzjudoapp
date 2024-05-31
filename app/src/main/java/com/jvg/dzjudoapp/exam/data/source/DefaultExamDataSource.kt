package com.jvg.dzjudoapp.exam.data.source

import com.jvg.dzjudoapp.exam.data.local.ExamLocal

class DefaultExamDataSource(
    override val examLocal: ExamLocal
) : ExamDataSource
