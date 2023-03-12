package com.example.weektimetable.domain.repository

import com.example.weektimetable.domain.entity.WeekDateEntity
import com.example.weektimetable.domain.entity.WeekEntity

interface IWeekTimeTableRepository {
    suspend fun getWeekTimetableByGroup(number: String, date: WeekDateEntity): WeekEntity
    suspend fun getWeekTimetableByAuditory(id: String, date: WeekDateEntity): WeekEntity
    suspend fun getWeekTimetableByTeacher(id: String, date: WeekDateEntity): WeekEntity
}