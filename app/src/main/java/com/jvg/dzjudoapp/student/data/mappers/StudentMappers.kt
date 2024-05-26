package com.jvg.dzjudoapp.student.data.mappers

import com.jvg.dzjudoapp.student.domain.model.Student
import com.jvg.dzjudoapp.Student as StudentEntity

fun StudentEntity.toDomain(): Student = Student(
    id = id,
    name = name,
    lastname = lastname,
    birthday = birthday,
    address = address,
    phone = phone,
    email = email,
    startDate = start_date,
    belt = belt,
    representativeName = representative_name,
    emergencyPhone = emergency_phone,
    active = active,
)

fun Student.toDatabase(): StudentEntity = StudentEntity(
    id = id,
    name = name,
    lastname = lastname,
    birthday = birthday,
    address = address,
    phone = phone,
    email = email,
    start_date = startDate,
    belt = belt,
    representative_name = representativeName,
    emergency_phone = emergencyPhone,
    active = active,
)
