package com.jvg.dzjudoapp.student.data.source

import com.jvg.dzjudoapp.student.data.local.StudentLocal

interface StudentDataSource {
    val studentLocal: StudentLocal
}
