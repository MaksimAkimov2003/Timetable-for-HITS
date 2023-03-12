package com.example.userstorage.data.storage

import android.content.SharedPreferences
import com.example.userstorage.domain.entity.TimetableType
import com.example.userstorage.domain.entity.TimetableTypeEntity
import com.example.userstorage.domain.entity.UserData

private const val userTimetableTypeKey = "USER_TIMETABLE_TYPE_KEY"
private const val userTimetableTypeValueKey = "USER_TIMETABLE_TYPE_VALUE_KEY"
class UserStorage(private val sharedPrefs: SharedPreferences): IUserStorage {

    override fun getUserData(): UserData {
        val type = sharedPrefs.getInt(userTimetableTypeKey, TimetableTypeEntity.Unauthorized.key)
        val value = sharedPrefs.getString(userTimetableTypeValueKey, "")
        val data = UserData(
            data = when(type) {
                0       -> TimetableType.Group
                1       -> TimetableType.Teacher
                2       -> TimetableType.Auditory
                else    -> TimetableType.Unauthorized
            }
        )
        data.data.value = value ?: ""
        return data
    }

    override fun saveUserData(data: UserData) {
        if (getUserData().data == TimetableType.Unauthorized) {
            val type = when (data.data) {
                TimetableType.Unauthorized -> TimetableTypeEntity.Unauthorized.key
                TimetableType.Group        -> TimetableTypeEntity.Group.key
                TimetableType.Teacher      -> TimetableTypeEntity.Teacher.key
                TimetableType.Auditory     -> TimetableTypeEntity.Auditory.key
            }
            val value = data.data.value
            putTimetableType(type, value)
        }
    }

    override fun clearUserData() {
        putTimetableType(TimetableTypeEntity.Unauthorized.key, "")
    }

    private fun putTimetableType(type: Int, value: String) {
        sharedPrefs
            .edit()
            .putInt(userTimetableTypeKey, type)
            .putString(userTimetableTypeValueKey, value)
            .apply()
    }

}