package com.example.userstorage.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class UserData(val data: TimetableType)

enum class TimetableTypeEntity(val key: Int) {
    Unauthorized(-1),
    Group(0),
    Teacher(1),
    Auditory(2),
}

@Parcelize
enum class TimetableType(val prefix: String, var value: String = "") : Parcelable {

    Unauthorized(""),
    Group("Группа"),
    Teacher(""),
    Auditory("Аудитория"),
}