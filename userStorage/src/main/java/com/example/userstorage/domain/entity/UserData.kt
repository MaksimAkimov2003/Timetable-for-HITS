package com.example.userstorage.domain.entity

data class UserData(val data: TimetableType)

enum class TimetableTypeEntity(val key: Int) {
    Unauthorized(-1),
    Group(0),
    Teacher(1),
    Auditory(2),
}

enum class TimetableType(val prefix: String, var value: String = "") {
    Unauthorized(""),
    Group("Группа"),
    Teacher(""),
    Auditory("Аудитория"),
}