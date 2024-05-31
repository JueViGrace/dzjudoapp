package com.jvg.dzjudoapp.exam.data.mappers

import com.jvg.dzjudoapp.exam.domain.model.Exam
import com.jvg.dzjudoapp.Exam as ExamEntity

fun ExamEntity.toDomain(): Exam = Exam(
    id = id,
    name = name
)

fun Exam.toDatabase(): ExamEntity = ExamEntity(
    id = id,
    name = name
)