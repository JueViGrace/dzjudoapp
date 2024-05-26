package com.jvg.dzjudoapp.student.data.source

import com.jvg.dzjudoapp.student.data.local.StudentLocal

class DefaultStudentDataSource(
    override val studentLocal: StudentLocal
) : StudentDataSource
