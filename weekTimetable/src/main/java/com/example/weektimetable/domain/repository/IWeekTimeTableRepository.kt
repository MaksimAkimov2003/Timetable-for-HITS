package com.example.weektimetable.domain.repository

import com.example.weektimetable.domain.entity.WeekEntity

interface IWeekTimeTableRepository {
    suspend fun getWeekTimetableByGroup(number: String): WeekEntity
    suspend fun getWeekTimetableByAuditory(id: String): WeekEntity
    suspend fun getWeekTimetableByTeacher(id: String): WeekEntity
}