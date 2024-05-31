package com.jvg.dzjudoapp.exam.domain.model

import java.util.UUID

data class Exam(
    val id: String = UUID.randomUUID().toString(),
    val name: String = ""
)
