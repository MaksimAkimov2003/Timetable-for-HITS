package com.example.userstorage.domain.entity

data class UserData(val data: TimetableType)

enum class TimetableType(val prefix: String, var value: String = "") {
    Group("Группа"),
    Teacher(""),
    Auditory("Аудитория"),
}