package com.example.weektimetable.domain.repository

import com.example.weektimetable.data.dto.WeekDto
import com.example.weektimetable.domain.entity.WeekTimetableEntity
import retrofit2.http.Path

interface IWeekTimeTableRepository {
    suspend fun getWeekTimetableByGroup(number: String): WeekTimetableEntity
    suspend fun getWeekTimetableByAuditory(id: String): WeekTimetableEntity
    suspend fun getWeekTimetableByTeacher(id: String): WeekTimetableEntity
}