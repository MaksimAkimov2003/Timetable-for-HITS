package com.example.userstorage.domain.entity

data class UserData(val data: TimetableType)

enum class TimetableTypeEntity(val key: Int) {
    Group(0),
    Teacher(1),
    Auditory(2),
}

enum class TimetableType(val prefix: String, var value: String = "") {
    Group("Группа"),
    Teacher(""),
    Auditory("Аудитория"),
}