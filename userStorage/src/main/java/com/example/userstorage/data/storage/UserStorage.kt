package com.example.userstorage.data.storage

import android.content.SharedPreferences
import com.example.userstorage.domain.entity.TimetableType
import com.example.userstorage.domain.entity.UserData

private const val userTimetableTypeKey = "USER_TIMETABLE_TYPE_KEY"
private const val userTimetableTypeValueKey = "USER_TIMETABLE_TYPE_VALUE_KEY"
class UserStorage(private val sharedPrefs: SharedPreferences): IUserStorage {

    override fun getUserData(): UserData {
        val type = sharedPrefs.getInt(userTimetableTypeKey, -1)
        val value = sharedPrefs.getString(userTimetableTypeValueKey, "")
        val data = UserData(
            data = when(type) {
                0 -> TimetableType.Group
                1 -> TimetableType.Teacher
                2 -> TimetableType.Auditory
                else -> TimetableType.Group
            }
        )
        data.data.value = value?: ""
        return data
    }

    override fun saveUserData(data: UserData) {
        val type = when(data.data) {
            TimetableType.Group -> TimetableTypeEntity.Group.key
            TimetableType.Teacher -> TimetableTypeEntity.Teacher.key
            TimetableType.Auditory -> TimetableTypeEntity.Auditory.key
        }
        val value = data.data.value
        sharedPrefs
            .edit()
            .putInt(userTimetableTypeKey, type)
            .putString(userTimetableTypeValueKey, value)
            .apply()
    }

}

enum class TimetableTypeEntity(val key: Int) {
    Group(0),
    Teacher(1),
    Auditory(2),
}